package com.ctream.scholar.conversations.service;

import com.ctream.scholar.conversations.api.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    private final Map<UUID, ConversationDetail> conversationMap = new ConcurrentHashMap<>();

    public void addConversation( ConversationDetail conversationDetail ) {
        this.conversationMap.put( conversationDetail.getConversationId(), conversationDetail );
    }


    public List<ConversationOverViewWrapper> getAllAsOverview() {
        return conversationMap.values()
                .stream()
                .map( this::toConversationOverview )
                .collect( Collectors.groupingBy( c -> c.getCreated().toLocalDate() ) )
                .entrySet()
                .stream()
                .map( entry -> {
                    var wrapper = new ConversationOverViewWrapper();
                    wrapper.setDate( entry.getKey() );
                    wrapper.setConversations( entry.getValue() );
                    return wrapper;
                } ).toList();
    }

    public List<ConversationDetail> getAllDetailed() {
        return new ArrayList<>( conversationMap.values() );
    }

    public ConversationDetail createNewConversation() {
        var conversationId = UUID.randomUUID();
        var currentDate = LocalDateTime.now();

        var detail = new ConversationDetail();
        detail.setTitle( "Conversation " + currentDate.toLocalDate() );
        detail.setConversationId( conversationId );
        detail.setCreated( currentDate );
        detail.setMessages( new ArrayList<>() );

        this.conversationMap.put( conversationId, detail );
        return detail;
    }

    public void appendMessageToConversation( UUID conversationId, ConversationMessageRequest conversationMessageRequest ) {

        var existingConversationDetail = this.conversationMap.get( conversationId );
        if ( Objects.isNull( existingConversationDetail ) ) {
            throw new IllegalArgumentException( "Conversation with id " + conversationId + " does not exist" );
        }

        var conversationMessage = toConversationMessage( conversationMessageRequest );

        existingConversationDetail.addMessagesItem( conversationMessage );
        existingConversationDetail.setUpdated( LocalDateTime.now() );

        this.conversationMap.put( conversationId, existingConversationDetail );
    }

    public ConversationDetail getSingleOne( UUID conversationId ) {
        return this.conversationMap.get( conversationId );
    }

    private ConversationMessage toConversationMessage( ConversationMessageRequest conversationMessageRequest ) {
        var conversationMessage = new ConversationMessage();

        conversationMessage.setMessageId( UUID.randomUUID() );
        conversationMessage.setCreated( LocalDateTime.now() );
        conversationMessage.setContent( conversationMessageRequest.getContent() );
        conversationMessage.setAuthor( conversationMessageRequest.getAuthor() );
        conversationMessage.setFinished( LocalDateTime.now() );

        return conversationMessage;
    }

    private ConversationOverview toConversationOverview( ConversationDetail conversationDetail ) {
        var overview = new ConversationOverview();
        overview.setConversationId( conversationDetail.getConversationId() );
        overview.setCreated( conversationDetail.getCreated() );
        overview.setFinished( conversationDetail.getFinished() );
        overview.setMessagesLength( conversationDetail.getMessages().size() );
        overview.setTitle( conversationDetail.getTitle() );
        return overview;
    }


    public List<ConversationAnalytics> getAnalytics() {

        var groupedByDate = this.conversationMap.values()
                .stream()
                .collect( Collectors.groupingBy( ConversationDetail::getCreated ) );

        return groupedByDate.entrySet()
                .stream()
                .map( entry -> {
                    var currentDate = entry.getKey();
                    var averageMessageCount = getAverageMessageCount( entry.getValue() );
                    var conversationCount = Objects.nonNull( entry.getValue() ) ? entry.getValue().size() : 0;
                    var countOfUserMessages = getCountOfMessagesByAuthor( entry.getValue(), MessageAuthor.USER );
                    var countOfBotMessages = getCountOfMessagesByAuthor( entry.getValue(), MessageAuthor.BOT );

                    var analytics = new ConversationAnalytics();
                    analytics.setDate( currentDate.toLocalDate() );
                    analytics.setAverageMessages( averageMessageCount );
                    analytics.setConversationCount( conversationCount );
                    analytics.setMessageOfUserCount( countOfUserMessages );
                    analytics.setMessageOfBotCount( countOfBotMessages );

                    return analytics;

                } ).toList();
    }

    private Integer getCountOfMessagesByAuthor( List<ConversationDetail> conversationDetailList, MessageAuthor author ) {
        return conversationDetailList
                .stream()
                .map( cl -> {
                            return cl.getMessages()
                                    .stream()
                                    .collect( Collectors.groupingBy( ConversationMessage::getAuthor ) );
                        }
                )
                .filter( map -> map.get( author ) != null )
                .map( map -> map.get( author ).size() )
                .findFirst()
                .orElse( 0 );
    }

    private Integer getAverageMessageCount( List<ConversationDetail> conversationDetailList ) {
        var totalMessages = conversationDetailList.stream()
                .mapToInt( cl -> cl.getMessages().size() )
                .sum();

        return Math.divideExact( totalMessages, conversationDetailList.size() );
    }
}

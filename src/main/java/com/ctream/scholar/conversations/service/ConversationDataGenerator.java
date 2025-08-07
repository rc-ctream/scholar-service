package com.ctream.scholar.conversations.service;

import com.ctream.scholar.conversations.api.model.ConversationDetail;
import com.ctream.scholar.conversations.api.model.ConversationMessage;
import com.ctream.scholar.conversations.api.model.MessageAuthor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ConversationDataGenerator {

    private final ConversationService conversationService;

    @EventListener( ApplicationReadyEvent.class )
    public void initData() {
        var random = new Random();

        if ( conversationService.getAllDetailed().isEmpty() ) {

            IntStream.range( 0, 7 )
                    .mapToObj( index -> LocalDateTime.now().minusDays( index ) )
                    .forEach( currentDateTime -> {

                        // create a random number of conversations for each day `currentDateTime`
                        IntStream.range( 0, 5 + random.nextInt( 6 ) )
                                .forEach( unused -> {
                                    var newConversation = new ConversationDetail();
                                    newConversation.setConversationId( UUID.randomUUID() );
                                    newConversation.setTitle( "Conversation - " + unused );
                                    newConversation.setCreated( currentDateTime );

                                    newConversation.setMessages( createMessages( currentDateTime, random ) );
                                    newConversation.setFinished( currentDateTime.plusMinutes( 10 ) );

                                    conversationService.addConversation( newConversation );
                                } );
                    } );


        }

    }

    private List<ConversationMessage> createMessages( LocalDateTime date, Random random ) {

        return IntStream.range( 0, 10 + random.nextInt( 11 ) )
                .mapToObj( unused -> {
                    var message = new ConversationMessage();
                    message.setCreated( date );
                    message.setMessageId( UUID.randomUUID() );
                    message.setAuthor( randomAuthor( unused ) );
                    message.setContent( generateLoremIpsum( 4 + random.nextInt( 10 ) ) );
                    message.setFinished( date.plusSeconds( unused ) );
                    return message;
                } ).collect( Collectors.toCollection( ArrayList::new ) );
    }

    private MessageAuthor randomAuthor( int unused ) {
        return unused % 2 == 0 ? MessageAuthor.BOT : MessageAuthor.USER;
    }

    public String generateLoremIpsum( int wordCount ) {
        String[] loremWords = (
                "lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua "
                        + "ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat duis aute irure dolor "
                        + "in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur excepteur sint occaecat cupidatat non proident "
                        + "sunt in culpa qui officia deserunt mollit anim id est laborum"
        ).split( " " );

        var random = new Random();
        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < wordCount; i++ ) {
            if ( i > 0 ) sb.append( " " );
            sb.append( loremWords[random.nextInt( loremWords.length )] );
        }

        sb.append( "." );
        return Character.toUpperCase( sb.charAt( 0 ) ) + sb.substring( 1 );
    }

}

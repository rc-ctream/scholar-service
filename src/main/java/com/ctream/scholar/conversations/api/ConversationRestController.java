package com.ctream.scholar.conversations.api;

import com.ctream.scholar.conversations.api.model.*;
import com.ctream.scholar.conversations.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ConversationRestController implements ConversationsApi {

    private final ConversationService conversationService;

    @Override
    public ResponseEntity<List<ConversationOverViewWrapper>> getAllConversations() {
        return ResponseEntity.ok( conversationService.getAllAsOverview() );
    }

    @Override
    public ResponseEntity<ConversationDetail> getSingleConversation( UUID conversationId ) {
        var conversationDetail = conversationService.getSingleOne( conversationId );
        return ResponseEntity.ok( conversationDetail );
    }

    @Override
    public ResponseEntity<ConversationIdResponse> createNewConversation() {
        var conversationDetail = conversationService.createNewConversation();
        return ResponseEntity.ok( toConversationIdResponse( conversationDetail ) );
    }

    @Override
    public ResponseEntity<Void> appendSingleMessage( UUID conversationId,
                                                     ConversationMessageRequest conversationMessageRequest ) {

        conversationService.appendMessageToConversation(
                conversationId,
                conversationMessageRequest
        );

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ConversationAnalytics>> getAnalytics() {
        var analytics = conversationService.getAnalytics();
        return ResponseEntity.ok( analytics );
    }

    private ConversationIdResponse toConversationIdResponse( ConversationDetail conversationDetail ) {
        var response = new ConversationIdResponse();
        response.setTitle( conversationDetail.getTitle() );
        response.setConversationId( conversationDetail.getConversationId() );
        response.setCreated( conversationDetail.getCreated() );
        return response;
    }
}

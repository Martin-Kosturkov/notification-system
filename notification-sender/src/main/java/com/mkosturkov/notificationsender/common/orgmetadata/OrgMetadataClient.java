package com.mkosturkov.notificationsender.common.orgmetadata;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Optional;

@Component
public class OrgMetadataClient {

    private final RestClient restClient;

    public OrgMetadataClient(@Qualifier("org-metadata-client") RestClient restClient) {
        this.restClient = restClient;
    }

    public Optional<UserMetadata> getUserMetadataById(int id) {
        try {
            return Optional.ofNullable(restClient.get()
                    .uri("/users/{id}", id)
                    .retrieve()
                    .body(UserMetadata.class));

        } catch (RestClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Optional.empty();
            }

            throw new RuntimeException("Error getting user metadata for id: " + id);
        }
    }
}

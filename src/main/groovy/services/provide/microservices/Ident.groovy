package services.provide.client.microservices

import services.provide.client.ApiClient

/*
 * Ident microservice; provides access to functionality
 * exposed by the Provide identity and resource authorization APIs.
 */
 public class Ident {

    private static final DEFAULT_HOST = 'ident.provide.services'

    /**
     * Static initializer for constructing API client instances.
     */
    static def init(token) {
        return new Ident(token)
    }

    private def client

    private def Ident(token) {
        def scheme = System.getenv('IDENT_API_SCHEME')
        def host = System.getenv('IDENT_API_HOST')
        if (!host)
            host = DEFAULT_HOST
        this.client = ApiClient.init(scheme, host, token)
    }

    def createApplication(params) {
        client.post('applications', params)
    }

    def updateApplication(application_id, params) {
        client.put("applications/#{application_id}", params)
    }

    def fetchApplications(params) {
        client.get('applications', params)
    }

    def fetchApplicationDetails(app_id) {
        client.get("applications/#{app_id}")
    }

    def fetchApplicationTokens(app_id) {
        client.get("applications/#{app_id}/tokens")
    }

    def authenticate(params) {
        client.post('authenticate', params)
    }

    def fetchTokens(params) {
        client.get('tokens', params)
    }

    def fetchTokenDetails(token_id) {
        client.get("tokens/#{token_id}")
    }

    def deleteToken(token_id) {
        client.delete("tokens/#{token_id}")
    }

    def createUser(params) {
        client.post('users', params)
    }

    def fetchUsers() {
        client.get('users')
    }

    def fetchUserDetails(user_id) {
        client.get("users/#{user_id}")
    }

    def updateUser(user_id, params) {
        client.put("users/#{user_id}", params)
    }
}

package io.pragra.novbatch.httpclientfeb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("ClientController Tests")
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    // ============= Tests for getOrders() endpoint =============

    @Test
    @DisplayName("Should return status 200 when order is found")
    void testGetOrdersReturnsOkStatus() throws Exception {
        // Arrange
        Integer orderId = 1;
        ProductOrder order = ProductOrder.builder()
                .id(1)
                .productName("Laptop")
                .orderValue(50000.0)
                .quantity(2)
                .build();

        when(clientService.getOrder(orderId)).thenReturn(order);

        // Act & Assert
        mockMvc.perform(get("/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productName").value("Laptop"))
                .andExpect(jsonPath("$.orderValue").value(50000.0))
                .andExpect(jsonPath("$.quantity").value(2));

        verify(clientService, times(1)).getOrder(orderId);
    }

    @Test
    @DisplayName("Should return correct product order details")
    void testGetOrdersReturnsCorrectDetails() throws Exception {
        // Arrange
        Integer orderId = 5;
        ProductOrder order = ProductOrder.builder()
                .id(5)
                .productName("Mouse")
                .orderValue(500.0)
                .quantity(10)
                .build();

        when(clientService.getOrder(orderId)).thenReturn(order);

        // Act & Assert
        mockMvc.perform(get("/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Mouse"))
                .andExpect(jsonPath("$.orderValue").value(500.0))
                .andExpect(jsonPath("$.quantity").value(10));

        verify(clientService, times(1)).getOrder(orderId);
    }



    @Test
    @DisplayName("Should call service with correct order id")
    void testGetOrdersCallsServiceWithCorrectId() throws Exception {
        // Arrange
        Integer orderId = 3;
        ProductOrder order = new ProductOrder();
        when(clientService.getOrder(orderId)).thenReturn(order);

        // Act
        mockMvc.perform(get("/{id}", orderId))
                .andExpect(status().isOk());

        // Assert
        verify(clientService, times(1)).getOrder(orderId);
    }

    @Test
    @DisplayName("Should handle multiple consecutive requests")
    void testGetOrdersHandlesMultipleRequests() throws Exception {
        // Arrange
        ProductOrder order1 = ProductOrder.builder()
                .id(1)
                .productName("Product1")
                .orderValue(100.0)
                .quantity(1)
                .build();

        ProductOrder order2 = ProductOrder.builder()
                .id(2)
                .productName("Product2")
                .orderValue(200.0)
                .quantity(2)
                .build();

        when(clientService.getOrder(1)).thenReturn(order1);
        when(clientService.getOrder(2)).thenReturn(order2);

        // Act & Assert
        mockMvc.perform(get("/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Product1"));

        mockMvc.perform(get("/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Product2"));

        verify(clientService, times(1)).getOrder(1);
        verify(clientService, times(1)).getOrder(2);
    }

    @Test
    @DisplayName("Should return order with zero quantity")
    void testGetOrdersWithZeroQuantity() throws Exception {
        // Arrange
        Integer orderId = 2;
        ProductOrder order = ProductOrder.builder()
                .id(2)
                .productName("Keyboard")
                .orderValue(2000.0)
                .quantity(0)
                .build();

        when(clientService.getOrder(orderId)).thenReturn(order);

        // Act & Assert
        mockMvc.perform(get("/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(0));
    }

    @Test
    @DisplayName("Should return order with null productName")
    void testGetOrdersWithNullProductName() throws Exception {
        // Arrange
        Integer orderId = 4;
        ProductOrder order = ProductOrder.builder()
                .id(4)
                .productName(null)
                .orderValue(300.0)
                .quantity(3)
                .build();

        when(clientService.getOrder(orderId)).thenReturn(order);

        // Act & Assert
        mockMvc.perform(get("/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(nullValue()));
    }

    // ============= Tests for getUser() endpoint =============

    @Test
    @DisplayName("Should return status 200 when GitHub user is found")
    void testGetUserReturnsOkStatus() throws Exception {
        // Arrange
        String userId = "octocat";
        GitHubUser user = GitHubUser.builder()
                .login("octocat")
                .id("1")
                .node_id("MDQ6VXNlcjE=")
                .avatar_url("https://avatars.githubusercontent.com/u/1?v=4")
                .url("https://api.github.com/users/octocat")
                .html_url("https://github.com/octocat")
                .build();

        when(clientService.getUser(userId)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/github/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.login").value("octocat"))
                .andExpect(jsonPath("$.id").value("1"));

        verify(clientService, times(1)).getUser(userId);
    }

    @Test
    @DisplayName("Should return correct GitHub user details")
    void testGetUserReturnsCorrectDetails() throws Exception {
        // Arrange
        String userId = "torvalds";
        GitHubUser user = GitHubUser.builder()
                .login("torvalds")
                .id("1024500")
                .node_id("MDQ6VXNlcjEwMjQ1MDA=")
                .avatar_url("https://avatars.githubusercontent.com/u/1024500?v=4")
                .gravatar_id("")
                .url("https://api.github.com/users/torvalds")
                .html_url("https://github.com/torvalds")
                .followers_url("https://api.github.com/users/torvalds/followers")
                .following_url("https://api.github.com/users/torvalds/following{/other_user}")
                .build();

        when(clientService.getUser(userId)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/github/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("torvalds"))
                .andExpect(jsonPath("$.id").value("1024500"))
                .andExpect(jsonPath("$.html_url").value("https://github.com/torvalds"));

        verify(clientService, times(1)).getUser(userId);
    }



    @Test
    @DisplayName("Should call service with correct userId")
    void testGetUserCallsServiceWithCorrectUserId() throws Exception {
        // Arrange
        String userId = "testuser";
        GitHubUser user = new GitHubUser();
        when(clientService.getUser(userId)).thenReturn(user);

        // Act
        mockMvc.perform(get("/github/{userId}", userId))
                .andExpect(status().isOk());

        // Assert
        verify(clientService, times(1)).getUser(userId);
    }

    @Test
    @DisplayName("Should handle special characters in userId")
    void testGetUserHandlesSpecialCharactersInUserId() throws Exception {
        // Arrange
        String userId = "user-with-dash";
        GitHubUser user = GitHubUser.builder()
                .login("user-with-dash")
                .id("999")
                .build();

        when(clientService.getUser(userId)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/github/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("user-with-dash"));

        verify(clientService, times(1)).getUser(userId);
    }

    @Test
    @DisplayName("Should handle multiple consecutive GitHub user requests")
    void testGetUserHandlesMultipleRequests() throws Exception {
        // Arrange
        GitHubUser user1 = GitHubUser.builder()
                .login("user1")
                .id("1")
                .build();

        GitHubUser user2 = GitHubUser.builder()
                .login("user2")
                .id("2")
                .build();

        when(clientService.getUser("user1")).thenReturn(user1);
        when(clientService.getUser("user2")).thenReturn(user2);

        // Act & Assert
        mockMvc.perform(get("/github/{userId}", "user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("user1"));

        mockMvc.perform(get("/github/{userId}", "user2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("user2"));

        verify(clientService, times(1)).getUser("user1");
        verify(clientService, times(1)).getUser("user2");
    }

    @Test
    @DisplayName("Should return user with all fields populated")
    void testGetUserWithAllFieldsPopulated() throws Exception {
        // Arrange
        String userId = "complete";
        GitHubUser user = GitHubUser.builder()
                .login("complete")
                .id("100")
                .node_id("node123")
                .avatar_url("https://example.com/avatar.jpg")
                .gravatar_id("gravatar123")
                .url("https://api.github.com/users/complete")
                .html_url("https://github.com/complete")
                .followers_url("https://api.github.com/users/complete/followers")
                .following_url("https://api.github.com/users/complete/following")
                .build();

        when(clientService.getUser(userId)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/github/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("complete"))
                .andExpect(jsonPath("$.id").value("100"))
                .andExpect(jsonPath("$.avatar_url").value("https://example.com/avatar.jpg"))
                .andExpect(jsonPath("$.html_url").value("https://github.com/complete"));

        verify(clientService, times(1)).getUser(userId);
    }

}


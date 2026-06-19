package io.pragra.novbatch.httpclientfeb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductOrder Model Tests")
class ProductOrderTest {

    @Test
    @DisplayName("Should create ProductOrder with builder")
    void testProductOrderBuilder() {
        // Arrange & Act
        ProductOrder order = ProductOrder.builder()
                .id(1)
                .productName("Laptop")
                .orderValue(50000.0)
                .quantity(2)
                .build();

        // Assert
        assertNotNull(order);
        assertEquals(1, order.getId());
        assertEquals("Laptop", order.getProductName());
        assertEquals(50000.0, order.getOrderValue());
        assertEquals(2, order.getQuantity());
    }

    @Test
    @DisplayName("Should create ProductOrder with no-arg constructor")
    void testProductOrderNoArgConstructor() {
        // Act
        ProductOrder order = new ProductOrder();

        // Assert
        assertNotNull(order);
        assertNull(order.getId());
        assertNull(order.getProductName());
        assertNull(order.getOrderValue());
        assertNull(order.getQuantity());
    }

    @Test
    @DisplayName("Should create ProductOrder with all-arg constructor")
    void testProductOrderAllArgConstructor() {
        // Act
        ProductOrder order = new ProductOrder(1, "Mouse", 500.0, 10);

        // Assert
        assertNotNull(order);
        assertEquals(1, order.getId());
        assertEquals("Mouse", order.getProductName());
        assertEquals(500.0, order.getOrderValue());
        assertEquals(10, order.getQuantity());
    }

    @Test
    @DisplayName("Should set and get ProductOrder properties")
    void testProductOrderSettersAndGetters() {
        // Arrange
        ProductOrder order = new ProductOrder();

        // Act
        order.setId(5);
        order.setProductName("Keyboard");
        order.setOrderValue(2000.0);
        order.setQuantity(5);

        // Assert
        assertEquals(5, order.getId());
        assertEquals("Keyboard", order.getProductName());
        assertEquals(2000.0, order.getOrderValue());
        assertEquals(5, order.getQuantity());
    }

    @Test
    @DisplayName("Should handle zero quantity in ProductOrder")
    void testProductOrderWithZeroQuantity() {
        // Act
        ProductOrder order = ProductOrder.builder()
                .id(3)
                .productName("Monitor")
                .orderValue(15000.0)
                .quantity(0)
                .build();

        // Assert
        assertEquals(0, order.getQuantity());
    }

    @Test
    @DisplayName("Should handle null productName in ProductOrder")
    void testProductOrderWithNullProductName() {
        // Act
        ProductOrder order = ProductOrder.builder()
                .id(4)
                .productName(null)
                .orderValue(300.0)
                .quantity(3)
                .build();

        // Assert
        assertNull(order.getProductName());
        assertEquals(4, order.getId());
    }

    @Test
    @DisplayName("Should handle decimal orderValue in ProductOrder")
    void testProductOrderWithDecimalOrderValue() {
        // Act
        ProductOrder order = ProductOrder.builder()
                .id(2)
                .productName("USB Cable")
                .orderValue(99.99)
                .quantity(1)
                .build();

        // Assert
        assertEquals(99.99, order.getOrderValue());
    }

    @Test
    @DisplayName("Should test ProductOrder equality")
    void testProductOrderEquality() {
        // Arrange
        ProductOrder order1 = ProductOrder.builder()
                .id(1)
                .productName("Laptop")
                .orderValue(50000.0)
                .quantity(2)
                .build();

        ProductOrder order2 = ProductOrder.builder()
                .id(1)
                .productName("Laptop")
                .orderValue(50000.0)
                .quantity(2)
                .build();

        // Act & Assert
        assertEquals(order1, order2);
    }

    @Test
    @DisplayName("Should test ProductOrder inequality")
    void testProductOrderInequality() {
        // Arrange
        ProductOrder order1 = ProductOrder.builder()
                .id(1)
                .productName("Laptop")
                .build();

        ProductOrder order2 = ProductOrder.builder()
                .id(2)
                .productName("Desktop")
                .build();

        // Act & Assert
        assertNotEquals(order1, order2);
    }

    @Test
    @DisplayName("Should generate ProductOrder toString")
    void testProductOrderToString() {
        // Arrange
        ProductOrder order = ProductOrder.builder()
                .id(1)
                .productName("Laptop")
                .orderValue(50000.0)
                .quantity(2)
                .build();

        // Act
        String toString = order.toString();

        // Assert
        assertNotNull(toString);
        assertTrue(toString.contains("1"));
        assertTrue(toString.contains("Laptop"));
    }

}

@DisplayName("GitHubUser Model Tests")
class GitHubUserTest {

    @Test
    @DisplayName("Should create GitHubUser with builder")
    void testGitHubUserBuilder() {
        // Arrange & Act
        GitHubUser user = GitHubUser.builder()
                .login("octocat")
                .id("1")
                .node_id("MDQ6VXNlcjE=")
                .avatar_url("https://avatars.githubusercontent.com/u/1?v=4")
                .gravatar_id("")
                .url("https://api.github.com/users/octocat")
                .html_url("https://github.com/octocat")
                .followers_url("https://api.github.com/users/octocat/followers")
                .following_url("https://api.github.com/users/octocat/following{/other_user}")
                .build();

        // Assert
        assertNotNull(user);
        assertEquals("octocat", user.getLogin());
        assertEquals("1", user.getId());
        assertEquals("MDQ6VXNlcjE=", user.getNode_id());
        assertEquals("https://avatars.githubusercontent.com/u/1?v=4", user.getAvatar_url());
    }

    @Test
    @DisplayName("Should create GitHubUser with no-arg constructor")
    void testGitHubUserNoArgConstructor() {
        // Act
        GitHubUser user = new GitHubUser();

        // Assert
        assertNotNull(user);
        assertNull(user.getLogin());
        assertNull(user.getId());
        assertNull(user.getNode_id());
    }

    @Test
    @DisplayName("Should create GitHubUser with all-arg constructor")
    void testGitHubUserAllArgConstructor() {
        // Act
        GitHubUser user = new GitHubUser("torvalds", "1024500", "MDQ6VXNlcjEwMjQ1MDA=",
                "https://avatars.githubusercontent.com/u/1024500?v=4", "gravatar123",
                "https://api.github.com/users/torvalds", "https://github.com/torvalds",
                "https://api.github.com/users/torvalds/followers",
                "https://api.github.com/users/torvalds/following{/other_user}");

        // Assert
        assertNotNull(user);
        assertEquals("torvalds", user.getLogin());
        assertEquals("1024500", user.getId());
        assertEquals("gravatar123", user.getGravatar_id());
    }

    @Test
    @DisplayName("Should set and get GitHubUser properties")
    void testGitHubUserSettersAndGetters() {
        // Arrange
        GitHubUser user = new GitHubUser();

        // Act
        user.setLogin("testuser");
        user.setId("123");
        user.setNode_id("node123");
        user.setAvatar_url("https://example.com/avatar.jpg");
        user.setGravatar_id("grav123");
        user.setUrl("https://api.github.com/users/testuser");
        user.setHtml_url("https://github.com/testuser");
        user.setFollowers_url("https://followers");
        user.setFollowing_url("https://following");

        // Assert
        assertEquals("testuser", user.getLogin());
        assertEquals("123", user.getId());
        assertEquals("node123", user.getNode_id());
        assertEquals("https://example.com/avatar.jpg", user.getAvatar_url());
        assertEquals("grav123", user.getGravatar_id());
        assertEquals("https://api.github.com/users/testuser", user.getUrl());
        assertEquals("https://github.com/testuser", user.getHtml_url());
        assertEquals("https://followers", user.getFollowers_url());
        assertEquals("https://following", user.getFollowing_url());
    }

    @Test
    @DisplayName("Should handle empty string properties in GitHubUser")
    void testGitHubUserWithEmptyStrings() {
        // Act
        GitHubUser user = GitHubUser.builder()
                .login("")
                .id("")
                .node_id("")
                .avatar_url("")
                .gravatar_id("")
                .url("")
                .html_url("")
                .followers_url("")
                .following_url("")
                .build();

        // Assert
        assertEquals("", user.getLogin());
        assertEquals("", user.getId());
        assertEquals("", user.getNode_id());
    }

    @Test
    @DisplayName("Should handle null properties in GitHubUser")
    void testGitHubUserWithNullProperties() {
        // Act
        GitHubUser user = GitHubUser.builder()
                .login("user1")
                .id(null)
                .node_id(null)
                .avatar_url(null)
                .build();

        // Assert
        assertEquals("user1", user.getLogin());
        assertNull(user.getId());
        assertNull(user.getNode_id());
        assertNull(user.getAvatar_url());
    }

    @Test
    @DisplayName("Should test GitHubUser equality")
    void testGitHubUserEquality() {
        // Arrange
        GitHubUser user1 = GitHubUser.builder()
                .login("octocat")
                .id("1")
                .build();

        GitHubUser user2 = GitHubUser.builder()
                .login("octocat")
                .id("1")
                .build();

        // Act & Assert
        assertEquals(user1, user2);
    }

    @Test
    @DisplayName("Should test GitHubUser inequality")
    void testGitHubUserInequality() {
        // Arrange
        GitHubUser user1 = GitHubUser.builder()
                .login("user1")
                .id("1")
                .build();

        GitHubUser user2 = GitHubUser.builder()
                .login("user2")
                .id("2")
                .build();

        // Act & Assert
        assertNotEquals(user1, user2);
    }

    @Test
    @DisplayName("Should generate GitHubUser toString")
    void testGitHubUserToString() {
        // Arrange
        GitHubUser user = GitHubUser.builder()
                .login("octocat")
                .id("1")
                .build();

        // Act
        String toString = user.toString();

        // Assert
        assertNotNull(toString);
        assertTrue(toString.contains("octocat"));
    }

    @Test
    @DisplayName("Should handle long URL properties in GitHubUser")
    void testGitHubUserWithLongUrls() {
        // Arrange & Act
        GitHubUser user = GitHubUser.builder()
                .login("torvalds")
                .id("1024500")
                .followers_url("https://api.github.com/users/torvalds/followers")
                .following_url("https://api.github.com/users/torvalds/following{/other_user}")
                .build();

        // Assert
        assertEquals("https://api.github.com/users/torvalds/followers", user.getFollowers_url());
        assertEquals("https://api.github.com/users/torvalds/following{/other_user}", user.getFollowing_url());
    }

}


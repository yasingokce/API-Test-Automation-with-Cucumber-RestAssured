Feature: As a user, I can create a hotel reservation

  Scenario: The user can create a hotel reservation and delete the reservation.
    Given User creates a new reservation
    And The user provides the information required for the reservation
    When User creates hotel reservation
    Then Reservation was successfully created
    And User cancels the created reservation

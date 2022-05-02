@tag
Feature: Board

  @tag1
  Scenario: element on board
    Given a board
    And a robot on a board
    And the board is set up
    And a checkpoint
    And coordinates 3 and 4
    And element at coordinates
    And element on board
    When coordinates are checked
    Then spot is not empty

  @tag2
  Scenario: element at random spot on board
    Given a board
    And a robot on a board
    And the board is set up
    And a checkpoint
    And element on random spot on board
    When check if element is on board
    Then element is on board

  @tag3
  Scenario: robot on the board
    Given a board
    And a robot on a board
    When the board is set up
    Then robot is on board
    And start of the robot
    And spot is not empty

  @tag4
  Scenario: initializing with n checkpoints
    Given a board
    And 3 checkpoints
    And a robot on a board
    When board is set up with checkpoints
    Then board contains n checkpoints
    And there is one more element than checkpoints






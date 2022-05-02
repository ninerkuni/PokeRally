@tag
Feature: Factory

  @tag1
  Scenario Outline: Factory with obstacles
    Given a <obstacle>
    And a factory
    When obstacle is added to factory
    Then obstacle is in factory

    Examples:
      | obstacle     |
      | pit			 |
      | gear		 |
      | conveyer	 |
      | trampoline   |

  @tag2
  Scenario Outline: build board with obstacles
    Given a factory with obstacles
    And a board with <robots>
    And <numObstacles> obstacles
    And <numCheckpoints> checkpoints
    When board set up with elements
    Then there are <numObstacles> obstacles on board

    Examples:
      | robots	   |	numObstacles	|	numCheckpoints	|
      | one robot  | 		4			|		2			|
      | two robots |  	    3			|		2			|
      | one robot  |        0           |       0           |

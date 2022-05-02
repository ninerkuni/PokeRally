@tag
  Feature: save state of game

    @tag1w
    Scenario Outline: save robot information
      Given a factory with obstacles
      And a board with <robots>
      And <numObstacles> obstacles
      And <numCheckpoints> checkpoints
      When board set up with elements
      And game is saved
      Then there are <numObstacles> obstacles on board
      And status of robot is written in csv

      Examples:
        | robots	 |	numObstacles	|	numCheckpoints	|
    #    | one robot  | 		4			|		2			|
        | two robots |  	2   		|		2			|

    @tag2
    Scenario Outline: save obstacle information
      Given a factory with obstacles
      And a board with <robots>
      And <numObstacles> obstacles
      And <numCheckpoints> checkpoints
      When board set up with elements
      And game is saved
      Then there are <numObstacles> obstacles on board
      And status of obstacle is written in csv

      Examples:
        | robots	 |	numObstacles	|	numCheckpoints	|
    #    | one robot  | 		4			|		2			|
        | two robots |  	2   		|		2			|


    @tag3
    Scenario Outline: save checkpoint information
      Given a factory with obstacles
      And a board with <robots>
      And <numObstacles> obstacles
      And <numCheckpoints> checkpoints
      When board set up with elements
      And game is saved
      Then there are <numObstacles> obstacles on board
      And status of checkpoint is written in csv

      Examples:
        | robots	 |	numObstacles	|	numCheckpoints	|
    #    | one robot  | 		4			|		2			|
        | two robots |  	20   		|		2			|

    @tag4
    Scenario Outline: save board information
      Given a factory with obstacles
      And a board with <robots>
      And <numObstacles> obstacles
      And <numCheckpoints> checkpoints
      When board set up with elements
      And game is saved
      Then there are <numObstacles> obstacles on board
      And status of board is written in csv

      Examples:
        | robots	 |	numObstacles	|	numCheckpoints	|
    #    | one robot  | 		4			|		2			|
        | two robots |  	20   		|		2			|

    @tag5
    Scenario Outline: saving game without any elements on board
      Given a factory with obstacles
      And a board with <robots>
      And <numObstacles> obstacles
      And <numCheckpoints> checkpoints
      When board set up with elements
      And game is started for multiplayer
      And game saved in the middle
      Then there are <numObstacles> obstacles on board
      And status of game is saved


      Examples:
        | robots	 |	numObstacles	|	numCheckpoints	|
    #    | one robot  | 		4			|		2			|
        | two robots |  	30   		|		2			|



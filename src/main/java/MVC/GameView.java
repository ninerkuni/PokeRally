package MVC;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameView extends View{
    //GameView is set up as BorderLayout with main global components:
    private JPanel titlePanel;
    private JPanel boardPanel;
    private JPanel handPanel;
    private JPanel orderPanel;
    private JPanel statsPanel;

    //hand can contain either buttons or labels
    private Container[] hand;
    private JLabel[] order;

    //board represented by matrix of element signature strings
    private String[][] board;
    private int dimensions;


    public GameView(Controller controller){
        super(controller);
        panel.setLayout(new BorderLayout());
        //components at CENTER, PAGE_END, PAGE_START, EAST and WEST are initiated
        initiateTitle();
        initiateHand();
        initiateOrder();
        initiateBoard();
        initiateStats();
        //panel is revalidated
        panel.revalidate();
        rally.add(panel);
        rally.revalidate();
        rally.pack();

    }

    public void update(){
        //individual components are updated
        resetHand();
        updateTitle();
        updateBoard();
        updateStats();
        rally.repaint();
        rally.revalidate();
        rally.pack();

    }

    private void initiateTitle(){
        //initiation of the title panel
        titlePanel = new DisplayPanel();
        titlePanel.setLayout(new GridBagLayout());
        //call reset method
        resetTitle();

    }

    private void resetTitle(){
        String message = "Poké Rally - "+controller.getActiveName()+"'s";
        //message changes slightly from single to multiplayer
        if(controller.getSingle()){
            message += " game";
        }
        else{
            message += " turn";
        }

        JLabel title = new JLabel(message,SwingConstants.CENTER);
        //increase font size
        title.setFont(new Font("Orange Kid", Font.BOLD, 50));
        //use predefined view GridBagContraints
        titlePanel.add(title,titleRow(c,0));
        panel.add(titlePanel,BorderLayout.PAGE_START);
        //panel is updated
        panel.revalidate();
        panel.repaint();
    }

    private void initiateBoard(){
        //necessary components are initiated
        boardPanel = new DisplayPanel();
        //dimensions are fetched from the controller
        dimensions = controller.getDimensions();
        //memory for board representation is allocated
        board = new String[dimensions][dimensions];
        //panel is set up accordingly
        boardPanel.setLayout(new GridLayout(dimensions, dimensions, 10, 10));

        //elements are loaded onto the board
        loadBoard();

        panel.add(boardPanel,BorderLayout.CENTER);

    }

    public void updateBoard(){
        loadBoard();
        //frame is revalidated
        rally.revalidate();
        rally.repaint();
        rally.pack();
    }

    public void updateHand(){
        //cards are loaded onto the hand panel according to signatures
        loadHand();
        //frame is revalidated
        rally.repaint();
        rally.revalidate();
        rally.pack();
    }

    public void updateStats(){
        statsPanel.removeAll();
        //relevant statistics are loaded in the controller and added to the panel
        controller.getStats();

        panel.revalidate();
        rally.repaint();
        rally.revalidate();
        rally.pack();
        System.out.println("Stats updated");
    }

    public void updateTitle(){
        //title is updated
        titlePanel.removeAll();
        resetTitle();

        rally.repaint();
        rally.pack();
    }

    private void loadBoard(){
        //boardpanel is cleaned
        boardPanel.removeAll();
        board = controller.loadBoard();

        //for each tile, a JLabel is created
        JLabel tile = new JLabel();
        for(int j = 0; j < dimensions; j++) {
            for (int i = 0; i < dimensions; i++) {
                try {
                    //JLabel is created by static method in Tile class
                    tile = Tile.tile(board[i][j]);
                }
                catch (IOException e){
                    //exception is again handled by showing an error message
                    showWarning("Image tile "+board[i][j]+" couldn't be found!");
                }
                boardPanel.add(tile);
            }
        }
        boardPanel.validate();
        boardPanel.repaint();
        panel.validate();
        panel.repaint();
    }

    private ImageIcon getPlaceholderImage(int i){
        //returns card icon for generic card num i
        ImageIcon icon = new ImageIcon();
        try {
            icon = CardImages.card("Card"+(i+1));
        }
        catch (IOException e){
            showWarning("Image tile couldn't be found!");
        }
        return icon;
    }
    private JLabel getPlaceholder(int i){
        //returns JLabel accoring to icon at position i
        JLabel spot;
        spot = new JLabel(getPlaceholderImage(i));
        spot.setAlignmentX(Component.CENTER_ALIGNMENT);
        return spot;
    }
    private JLabel[] addPlaceholders(int len){
        //creates array of JLabels containing placeholder icons
        JLabel[] p = new JLabel[len];

        for(int i = 0; i < len; i++){
            p[i] = getPlaceholder(i);
        }
        return p;
    }

    private JButton drawCards(){
        //button to draw cards
        JButton drawCards = new JButton("Draw");
        drawCards.addActionListener(
                e-> {
                    //invokes drawCard in the controller
                    controller.drawCards();
                    //updates hand
                    updateHand();
                }
        );
        return drawCards;
    }

    private JButton doneButton(){
        //button to submit order of cards
        JButton done = new JButton("Done");
        done.addActionListener(
                e-> {
                    if(controller.isOrdered()){
                        //invokes cards being executed
                        controller.playCardsOneByOne();
                        //resets hand and order panel
                        resetHand();
                        resetOrder();
                        //updates the entire frame
                        update();
                    }
                }
        );
        return done;
    }

    private void resetOrder() {
        //reset order panel
        orderPanel.removeAll();
        //add placeholder to panel
        order = addPlaceholders(order.length);
        paintCards(orderPanel,order);
        //add done button
        orderPanel.add(doneButton(),row(c,order.length));
        orderPanel.validate();
        panel.repaint();
    }

    private void initiateHand(){
        handPanel = new DisplayPanel();
        hand = new JLabel[controller.getCards().length];

        //hand panel is initiated with jlabels containing placeholder icons
        hand = addPlaceholders(hand.length);

        paintCards(handPanel,hand);

        handPanel.add(drawCards(),row(c,hand.length));

        panel.add(handPanel,BorderLayout.WEST);

    }

    private void resetHand(){
        handPanel.removeAll();
        //
        hand = addPlaceholders(hand.length);
        paintCards(handPanel,hand);
        handPanel.add(drawCards(),row(c,hand.length));
        handPanel.validate();
        panel.repaint();

    }

    private void initiateOrder(){
        orderPanel = new DisplayPanel();
        order = new JLabel[controller.getCards().length];

        order = addPlaceholders(order.length);
        paintCards(orderPanel,order);


        orderPanel.add(doneButton(),row(c,order.length));
        panel.add(orderPanel,BorderLayout.EAST);
    }


    private void paintCards(Container p, Container[] ls){
        // method that returns a list of containers to a parent container
        p.removeAll();
        for(int i = 0; i < ls.length;i++){
            if(!(ls[i] == null)) p.add(ls[i],row(c,i));
        }
        p.revalidate();
        p.repaint();
    }

    //global counter variable to connect positions in hand and order, reset after every round
    private int counter = 0;
    private void setOrder(JLabel label){
        if(counter < order.length) {
            order[counter] = label;
            paintCards(orderPanel, order);
        }
        orderPanel.add(doneButton(),row(c,order.length));
        orderPanel.repaint();
    }

    private void loadHand(){
        counter = 0;
        panel.remove(handPanel);
        //load cards on hand from controller
        String[] cards = controller.getCards();
        //clean hand[]
        hand = new JButton[cards.length];
        ImageIcon icon = new ImageIcon();

        //substitute the placeholder labels with specific card buttons
        for(int i = 0; i < cards.length; i++){
            String title = cards[i];
            try {
                icon = CardImages.card(title);
            }
            catch (IOException e){
                showWarning("Image tile couldn't be found!");
            }
            JButton spot = new JButton(icon);
            JLabel label = new JLabel(icon);
            spot.setAlignmentX(Component.CENTER_ALIGNMENT);
            spot.addActionListener(
                    e->{
                        //each button causes the connected card to be set to the position indicated by counter
                        //first card clicked is set to position one, second position two...
                        controller.setCardToPosition(title,counter);
                        //button is disabled after click
                        spot.setEnabled(false);
                        //card icon is replaced by default positon placeholder
                        spot.setIcon(getPlaceholderImage(counter));
                        //position indicated by counter in the order panel is updated accordingly
                        setOrder(label);
                        //hand panel is repainted
                        paintCards(handPanel,hand);
                        counter++;
                    }
            );
            //position in the container array hand is updated
            hand[i] = spot;
        }

        paintCards(handPanel,hand);
        panel.add(handPanel,BorderLayout.WEST);

    }


    public void addStats(String name, int[] stats) {
        int row = 0;
        //this method is called by the controller for each robot in the game
        DisplayPanel statistics = new DisplayPanel();

        //JLabels for name, checkpoint counter and score are created
        JLabel n = new JLabel(name);
        JLabel s = new JLabel("Score: "+stats[0]);
        JLabel check = new JLabel("Checkpoint Counter: "+stats[1]+"/"+controller.getNumCheckpoints());
        //added through GridBagContraints predefined in View
        statistics.add(n, row(c,row));
        row++;
        statistics.add(s,row(c,row));
        row++;
        statistics.add(check,row(c,row));

        //Panel(s) is/are added to stats panel
        this.statsPanel.add(statistics);
        statsPanel.repaint();
    }

    private void initiateStats(){
        //initiation of statistics panel
        statsPanel = new DisplayPanel();
        //controller is asked to add relevant stats
        controller.getStats();
        panel.add(statsPanel,BorderLayout.SOUTH);
        panel.repaint();
    }


}

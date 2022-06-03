/**
 * @file: GameController.java
 * @Author: Jianlan Ding - dingj29
 * @Date: Feb 12, 2022
 * @Description: controller module that handles inputs and links model and view module
 */
//package src;

public class GameController{

    // Define state variables
    private UserInterface view;
    private BoardT model;
    private static GameController controller = null;

    /**
     * @brief constructor
     * @param model - model module (BoardT)
     * @param view - view module (UseInterface)
     */
    private GameController(BoardT model, UserInterface view){
        this.model = model;
        this.view = view;
    }

    /**
     * @brief public static method for obtaining a single instance
     * @return the single GameController object
     */
    public static GameController getInstance(BoardT model, UserInterface view)
    {
        if (controller == null)
            controller = new GameController(model, view);

        return controller;
    }

    // get view
    public UserInterface getView() {
        return view;
    }

    // get model
    public BoardT getModel() {
        return model;
    }

    /**
     * @brief runs the game
     */
    public void runGame(){
        if (view != null){
            view.showView();
        }
    }
}



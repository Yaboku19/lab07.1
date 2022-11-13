package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberOutputView implements DrawNumberView {

    @Override
    public void setController(final DrawNumberController observer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription());
        
    }
    
}
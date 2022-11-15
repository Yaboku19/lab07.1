package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws SecurityException
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        /*app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberOutputView());
        app.addView(new DrawNumberSwingView()); */
        for (final String name: List.of("Output", "Swing")) {
            final String className = "it.unibo.mvc.view.DrawNumber" + name + "View";
            final Class<?> clas = Class.forName(className);
            for (int i=1;i<=3;i++) {
                final var newView = clas.getConstructor().newInstance();
                if (DrawNumberView.class.isAssignableFrom(newView.getClass())) {
                    app.addView((DrawNumberView)newView);
                }else {
                    throw new IllegalStateException(
                            newView.getClass() + " is not a subclass of " + DrawNumberView.class
                        );
                }
            }
            
            
        }
    }
}

package org.nice;

import com.formdev.flatlaf.FlatIntelliJLaf;
//import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import org.nice.Panels.*;
import org.nice.services.PokemonService;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class Main {
    private static final java.util.List<String> fakeLoadingMessages = List.of(
            "Warming up",
            "Gotta catch 'em all",
            "Loading pokedex.json",
            "Creating views",
            "Opening the app");

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();

        var disposeCall = PublishSubject.create();
        var random = new Random();
        var messageObservable = Observable.from(
                fakeLoadingMessages).concatMap(
                        message -> Observable.just(message).delay(
                                random.nextInt(200, 700),
                                TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()))
                .takeUntil(disposeCall);

        JFrame loadingFrame = initLoadingFrame(messageObservable);
        PokemonService.init();

        var footer = new Footer();
        var header = new Header();
        var body = new Body();

        initMainFrame(header, footer, body);
        disposeCall.onNext(null);
        loadingFrame.dispose();

    }

    private static JFrame initLoadingFrame(Observable<String> interval) {
        JFrame frame = new JFrame("Pokedex - Loading");
        ImageIcon AppIcon = new ImageIcon(Utils.getResource("/images/items/4.png"));
        frame.setIconImage(AppIcon.getImage());
        var root = new JPanel(new MigLayout("align center center, wrap, insets 12px", "grow"));

        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(480, 120);
        frame.setMinimumSize(new Dimension(480, 120));
        frame.setLocationRelativeTo(null);

        root.add(new JLabel("Initializing... Please wait"), "align center");
        var loadingBar = new JProgressBar();
        loadingBar.setIndeterminate(true);
        loadingBar.setForeground(new Color(0xE46666));
        root.add(loadingBar, "grow");

        var loadingMessage = new JLabel("Loading");
        loadingMessage.setFont(new Font(
                "Verdana",
                Font.ITALIC,
                10));
        root.add(loadingMessage, "align center");

        interval.subscribe(loadingMessage::setText);

        frame.add(root);
        frame.setVisible(true);
        return frame;
    }

    private static void initMainFrame(Header header, Footer footer, Body body) {
        JFrame frame = new JFrame("Pokedex");
        ImageIcon AppIcon = new ImageIcon(Utils.getResource("/images/items/4.png"));

        frame.setIconImage(AppIcon.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setMinimumSize(new Dimension(1080, 750)); // 720 + 30
        frame.setLocationRelativeTo(null); // center the window
        frame.setLayout(new BorderLayout(0, 0));

        frame.add(footer, BorderLayout.SOUTH);
        frame.add(body, BorderLayout.CENTER);
        frame.add(header, BorderLayout.NORTH);

        frame.setVisible(true);

    }
}

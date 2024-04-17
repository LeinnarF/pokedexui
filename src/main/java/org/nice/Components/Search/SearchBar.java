package org.nice.Components.Search;

import net.miginfocom.swing.MigLayout;
import org.nice.Components.shared.ComponentBorder;
import org.nice.lib.roundcorner.RoundedCorners;
import org.nice.services.SearchService;

import com.formdev.flatlaf.ui.FlatDropShadowBorder;

import rx.subjects.PublishSubject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Insets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SearchBar extends RoundedCorners {
    public SearchBar() {
        RoundedCorners Master = new RoundedCorners();
        setLayout(new MigLayout("center","0[grow]0","0[grow]0"));
        setBackground(new Color(0xE46666));
        setBorder(
        BorderFactory.createCompoundBorder(
                new FlatDropShadowBorder(Color.BLACK,new Insets(1,1,10,1) ,0.15f),
                new EmptyBorder(0,0,0,0)
            )
        );
        Master.setLayout(new MigLayout("align left", "grow"));
        Master.setBackground(new Color(0xffffff)); 
        var btn = new JButton("\uD83D\uDD0D");
        var field = new JTextField();
        field.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        Master.setAllRound(30);

        var cb = new ComponentBorder(btn);
        cb.install(field);
        Master.add(field, "grow");
        field.addActionListener(e -> {
            onSubmit(field.getText());
        });
        btn.addActionListener(e -> {
            onSubmit(field.getText());
        });
        PublishSubject<String> onTextChange = PublishSubject.create();
        onTextChange.debounce(500, TimeUnit.MILLISECONDS).subscribe(this::onSubmit);
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onTextChange.onNext(field.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onTextChange.onNext(field.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        add(Master,"grow");
    }


    void onSubmit(String s){
        SearchService.getInstance().setSearchString(s.trim());
    }
}

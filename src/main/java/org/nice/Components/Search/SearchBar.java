package org.nice.Components.Search;

import net.miginfocom.swing.MigLayout;
import org.nice.Components.shared.ComponentBorder;
import org.nice.lib.roundcorner.RoundedCorners;
import org.nice.services.SearchService;
import rx.subjects.PublishSubject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SearchBar extends RoundedCorners {
    public SearchBar() {
        setLayout(new MigLayout("align left", "grow"));
        setBackground(new Color(0xffffff)); 
        var btn = new JButton("\uD83D\uDD0D");
        var field = new JTextField();
        field.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        setAllRound(30);

        var cb = new ComponentBorder(btn);
        cb.install(field);
        add(field, "grow");
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
    }


    void onSubmit(String s){
        SearchService.getInstance().setSearchString(s.trim());
    }
}

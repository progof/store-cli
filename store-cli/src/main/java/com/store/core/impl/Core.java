package com.store.core.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.store.core.ICore;
import com.store.gui.IAuthGUI;
import com.store.model.User;
import com.store.service.Register;

@RequiredArgsConstructor
@Component
public class Core implements ICore {

    private final IAuthGUI authGUI;
    private final Register register;

    @Override
    public void launch() {
        boolean running = true;

        while (running) {
            switch (authGUI.showMenu()) {
                case "1":
                    authGUI.login();
                    break;
                case "2":
                    register(this.authGUI.register());
                    break;
                case "3":
                    running = false;
                    break;

                default:
                    break;
            }
        }

    }

    private void register(User register2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

}

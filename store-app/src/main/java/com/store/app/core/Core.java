package com.store.app.core;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import com.store.app.controller.CartController;
import com.store.app.db.Database;
import com.store.app.db.InitData;
import com.store.app.gui.AuthGUI;
import com.store.app.gui.StoreGUI;
import com.store.app.gui.DebugGUI;

@RequiredArgsConstructor
@Component
public class Core {
    private final CartController cart;
    private final AuthGUI authGUI;
    private final StoreGUI storeGUI;
    private final DebugGUI debugGUI;
    private final InitData initData;

    public void launch() {

        Database.init();
        initData.addSampleTeas();

        switch (authGUI.showAuthMentu()) {
            case "1":
                authGUI.register();
                break;
            case "2":
                if (authGUI.login()) {
                    runStore();
                }
                break;
            case "3":
                runDebug();
                break;
            case "4":
                System.out.println("✅ Exit.");
                System.exit(0);
                break;

            default:
                System.out.println("⛔️ Incorrect option.");
                break;
        }
    }

    public void runStore() {
        while (true) {

            switch (storeGUI.showStoreMenu()) {
                case "1":
                    storeGUI.listProducts();
                    break;
                case "2":
                    cart.addToCart();
                    break;
                case "3":
                    cart.showCart();
                    break;
                case "4":
                    cart.checkout();
                    break;
                case "5":
                    System.out.println("\n✅ Logout.");
                    return;
                default:
                    System.out.println("\n⛔️ Incorrect option.");
            }
        }
    }

    public void runDebug() {
        while (true) {
            switch (debugGUI.showDebugMenu()) {
                case "1":
                    debugGUI.listUsers();
                    break;
                case "2":
                    debugGUI.listProducts();
                    break;
                case "3":
                    launch();
                default:
                    System.out.println("⛔️ Incorrect option.");
                    break;
            }
        }
    }

}

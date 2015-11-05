package gameoftron;

/**
 * Gestionnaire de collision Implémente le pattern singleton
 *
 * @author Hikingyo & MLCDF
 */
public class Collider {

    private App app;
    private static Collider instance = null;

    private Collider(App app) {
        this.app = app;
    }

    public static Collider getInstance(App app) {
        if (instance == null) {
            instance = new Collider(app);
        }
        return instance;
    }

    /**
     * Test de is_possible d'une joueur en fonction de la direction du
     * mouvement. Si le pixel testé n'esst pas noir = collision
     *
     * @param direction char
     * @param j Joueur
     * @return boolean true si pas de collision (par défaut)
     */
    public Boolean is_possible(char direction, Joueur j) {
        Boolean is_possible = true;
        // Si le pixel est noir(background), il n'y a pas colision
        switch (direction) {
            case 'b': // Vers le bas
                if (app.get((int) j.getX(), (int) j.getY() + (int) app.taille + 1) != app.color(app.backgroundColor)) {
                    is_possible = false;
                }
                break;

            case 'h': // Vers le haut
                if (app.get((int) j.getX(), (int) j.getY() - 1) != app.color(app.backgroundColor)) {
                    is_possible = false;
                }
                break;

            case 'g': // Vers la gauche
                if (app.get((int) j.getX() - 1, (int) j.getY()) != app.color(app.backgroundColor)) {
                    is_possible = false;
                }
                break;

            case 'd': // Vers la droite
                if (app.get((int) j.getX() + (int) app.taille + 1, (int) j.getY()) != app.color(app.backgroundColor)) {
                    is_possible = false;
                }
                break;
            default:
                is_possible = true;
        }
        return is_possible;
    }
}

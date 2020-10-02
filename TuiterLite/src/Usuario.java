import java.awt.*;

public class Usuario {

    public static final int MIN_TUITES_SENIOR = 200;
    public static final int MIN_TUITES_NINJA = 1000;
    private int numeroTwitts;

    private final String email;
    private String nome;
    private Image foto;

    // Pode ser INICIANTE, SENIOR ou NINJA
    private NivelUsuario nivel;

    public Usuario(String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.nivel = NivelUsuario.INICIANTE;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public void setNivel(NivelUsuario nivel) {
        this.nivel = nivel;
    }

    public Image getFoto() {
        return this.foto;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    public NivelUsuario getNivel() {
        return nivel;
    }

    public int getNumeroTwitts() {
        return numeroTwitts;
    }

    public void setNumeroTwitts(int numeroTwitts) {
        this.numeroTwitts = numeroTwitts;
    }
}

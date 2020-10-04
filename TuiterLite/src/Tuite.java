import java.awt.*;
import java.util.ArrayList;

public class Tuite<T> {

    private final Usuario autor;
    private final String texto;
    private T anexo;
    private ArrayList<String> hashtags;

    // hashtags?
    // objeto anexado?

    public Tuite(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
        this.hashtags = new ArrayList<>();
    }

    public void anexarAlgo(T anexo) {
        this.anexo = anexo;
    }

    public Object getAnexo() { return this.anexo; }

    public Usuario getAutor() {
        return this.autor;
    }

    public String getTexto() {
        return this.texto;
    }

    public void zeraHashTags(){
        hashtags.clear();
    }

    public ArrayList<String> getHashtags() {

        zeraHashTags();

        String textos[] = texto.split("[\\s,!,...]");

        for (String palavra : textos){
            if (palavra.startsWith("#")){
                hashtags.add(palavra);
            }
        }
        return hashtags;
    }
}

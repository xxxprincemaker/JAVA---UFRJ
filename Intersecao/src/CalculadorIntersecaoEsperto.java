import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CalculadorIntersecaoEsperto extends CalculadorIntersecao<Integer> {

    private HashSet<Integer> listaInteligente;
    private int cont;
    public CalculadorIntersecaoEsperto() {
        this.listaInteligente = new HashSet<>();
    }

    @Override
    public int getQuantidadeElementosEmComum(List<Integer> lista1, List<Integer> lista2) {
        listaInteligente.addAll(lista1);
        for (Integer lista : lista2) {
            if (listaInteligente.contains(lista)){
                cont++;
            }
        }
        return cont;
    }

}

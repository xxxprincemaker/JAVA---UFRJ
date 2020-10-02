import java.util.ArrayList;
import java.util.Collections;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite<T> {

    public static final int TAMANHO_MAXIMO_TUITES = 120;
    private String emailAux;
    private Usuario usuariosCadastrados;
    private Tuite tuitesFeitos;
    private ArrayList<Usuario> usuariosCadastradosArrayList;
    private ArrayList<Tuite> tuitesFeitosArrayList;
    private String hashTagMaisComum;
    private ArrayList<RegistraMaiorHashtag> registros;
    private int numTwitts;

    public TuiterLite(){ this.usuariosCadastradosArrayList = new ArrayList<Usuario>();
                        this.tuitesFeitosArrayList = new ArrayList<Tuite>();
                        this.registros = new ArrayList<RegistraMaiorHashtag>();
                        }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * Se o email informado já estiver em uso, não faz nada e retorna null.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */

    public Usuario cadastrarUsuario(String nome, String email) {

        if (emailCadastrado(email)){
            return null;
        }

        usuariosCadastrados = new Usuario(nome, email);
        numTwitts = usuariosCadastrados.getNumeroTwitts();
        usuariosCadastradosArrayList.add(usuariosCadastrados);

//        if (emailCadastrado(this.email)){
//            return null;
//        }

        return this.usuariosCadastrados;
    }

    /**
     * Tuíta algo, retornando o objeto Tuíte criado.
     * Se o tamanho do texto exceder o limite pré-definido, não faz nada e retorna null.
     * Se o usuário não estiver cadastrado, não faz nada e retorna null.
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {

        //Testar se é maior que o maximo permitido.
        int cont = 0;
        for (int i = 0; i < texto.length(); i++){
            if (texto.charAt(i) != ' '){
                cont++;
            }
        }
        if (cont > TAMANHO_MAXIMO_TUITES){
            return null;
        }


        //Checa se o usuario existe.
        emailAux = usuario.getEmail();
        if (!(emailCadastrado(emailAux))){
            return null;
        }

        //Adiciona o ArrayList de Tuites.
        tuitesFeitos = new Tuite(usuario,texto);
        tuitesFeitosArrayList.add(tuitesFeitos);

        atualizaRank();

        //Printa no teclado o Twitt Feito.
        //System.out.println("Usuario: " + tuitesFeitos.getAutor().getNome() + "\n" + "Twitt: " + tuitesFeitos.getTexto());

        return tuitesFeitos;
    }

    public void atualizaRank(){
        usuariosCadastrados.setNumeroTwitts(numTwitts++);


        switch (numTwitts){
            case 200:
                usuariosCadastrados.setNivel(NivelUsuario.SENIOR);
                break;
            case 1000:
                usuariosCadastrados.setNivel(NivelUsuario.NINJA);
        }
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        int cont = 0;

        ArrayList todasHashtags = new ArrayList();
        ArrayList hashtags;
        ArrayList<Object> hashTagsRepetidas = new ArrayList<>();
        int ID;

        int occurrence;
        //Adiciona todas as hashtags de todos os Twitters digitados
        for (int i = 0; i < tuitesFeitosArrayList.size(); i++){
            hashtags = tuitesFeitosArrayList.get(i).getHashtags();
            todasHashtags.addAll(hashtags);
        }
        //Buscar a palavra mais dita.

        for (int i = 0; i < todasHashtags.size(); i++){

            hashTagsRepetidas.add(todasHashtags.get(i));
            occurrence = Collections.frequency(todasHashtags, todasHashtags.get(i));
            ID = i;
            Registra(occurrence, ID);


        }


        int iDMaiorHashTag = Maior();
        hashTagMaisComum = (String) todasHashtags.get(iDMaiorHashTag);
        return hashTagMaisComum;
    }
    //Checa o Maior
    public int Maior(){
        int maior = registros.get(0).getOccurrance();
        int ID = 0;

        for (int i = 0; i < registros.size(); i++){
            if (registros.get(i).getOccurrance() > maior){
                maior = registros.get(i).getOccurrance();
                ID = registros.get(i).getID();
            }
        }
    return ID;
    }

    public void Registra(int oc, int id){
        RegistraMaiorHashtag registro = new RegistraMaiorHashtag(id, oc);
        registros.add(registro);
    }

    public boolean emailCadastrado(String email){

        for (Usuario usuario : usuariosCadastradosArrayList) {
            this.emailAux = usuario.getEmail();
            if ((this.emailAux.compareTo(email)) == 0){
                return true;
            }
        }
        return false;
    }

     //Mainzinho bobo, apenas ilustrando String.split(regexp), e o String.startsWith()

//    public static void main(String[] args) {
//        String frase = "Testando algo,sdf com #hashtags no meio #teste vamos ver!fdfgf";
//        String[] palavras = frase.split("[\\s,!]");
//        for (String palavra : palavras) {
//            if (palavra.startsWith("#")) {
//                System.out.println(palavra);
//            }
//        }
//    }


}

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import skynet.SkynetGrafo;

//Suite de testes para os principais algoritmos utilizados na aplicação
public class SkynetTest {
    private SkynetGrafo skynet;

    @BeforeEach
    public void preparacao(){
        skynet = new SkynetGrafo("src/main/resources/grafo.txt");
    }

    @Test
    public void buscaEmLarguraTest1(){
        assertDoesNotThrow(() -> skynet.buscaEmLargura("Brasilia", "Singapura"));
        //Brasilia -> Sao Paulo -> Cidade do Cabo -> Sydney -> Singapura

        //Checando predecessores
        assertEquals(30, skynet.getBuscaLargura().getPredecessor()[29]);//pred[singapura] = sydney
        assertEquals(31, skynet.getBuscaLargura().getPredecessor()[30]);//pred[sydney] = cid. do cabo
        assertEquals(0, skynet.getBuscaLargura().getPredecessor()[31]);//pred[cid. do cabo] = sao paulo
        assertEquals(1, skynet.getBuscaLargura().getPredecessor()[0]);//pred[sao paulo] = brasilia
        
        //Distancia correta
        assertEquals(4, skynet.getBuscaLargura().getDistancia()[29]);
    }
    
    @Test
    public void buscaEmLarguraTest2(){
        assertDoesNotThrow(() -> skynet.buscaEmLargura("Vancouver", "Bagda"));
        //Vancouver -> Winnipeg -> Montreal -> Londres -> Oslo -> Moscou -> Bagda

        //Checando predecessores
        assertEquals(18, skynet.getBuscaLargura().getPredecessor()[38]);//pred[bagda] = moscou
        assertEquals(19, skynet.getBuscaLargura().getPredecessor()[18]);//pred[moscou] = oslo
        assertEquals(14, skynet.getBuscaLargura().getPredecessor()[19]);//pred[oslo] = londres
        assertEquals(6, skynet.getBuscaLargura().getPredecessor()[14]);//pred[londres] = montreal
        assertEquals(40, skynet.getBuscaLargura().getPredecessor()[6]);//pred[montreal] = winnipeg
        assertEquals(5, skynet.getBuscaLargura().getPredecessor()[40]);//pred[winnipeg] = vancouver
        
        //Distancia correta
        assertEquals(6, skynet.getBuscaLargura().getDistancia()[38]);
    }
    
    @Test
    public void caminhoCurtoTempoTest1(){
        assertDoesNotThrow(() -> skynet.caminhoCurtoTempo("Bogota", "Meca"));
        //Bogota (0,0h) -> Brasilia (5,0h) -> Lisboa (14,0h) -> Marrakech (15,5h) -> 
        //Cairo (20,5h) -> Meca (23,0h)

        //Checando predecessores
        assertEquals(22, skynet.getCaminhoCurto().getPredecessores()[28]);
        assertEquals(21, skynet.getCaminhoCurto().getPredecessores()[22]);
        assertEquals(11, skynet.getCaminhoCurto().getPredecessores()[21]);
        assertEquals(1, skynet.getCaminhoCurto().getPredecessores()[11]);
        assertEquals(7, skynet.getCaminhoCurto().getPredecessores()[1]);

        //Checando tempo
        assertEquals(5, skynet.getCaminhoCurto().getDistancia()[1]);
        assertEquals(14, skynet.getCaminhoCurto().getDistancia()[11]);
        assertEquals(15.5, skynet.getCaminhoCurto().getDistancia()[21]);
        assertEquals(20.5, skynet.getCaminhoCurto().getDistancia()[22]);
        assertEquals(23, skynet.getCaminhoCurto().getDistancia()[28]);
    } 
    
    @Test
    public void caminhoCurtoTempoTest2(){
        assertDoesNotThrow(() -> skynet.caminhoCurtoTempo("Lisboa", "Havai"));
        //Lisboa (0,0h) -> Montreal (6,5h) -> Winnipeg (9,5h) -> Vancouver (12,5h) -> Havai (18,0h) 
        
        //Checando predecessores
        assertEquals(5, skynet.getCaminhoCurto().getPredecessores()[39]);
        assertEquals(40, skynet.getCaminhoCurto().getPredecessores()[5]);
        assertEquals(6, skynet.getCaminhoCurto().getPredecessores()[40]);
        assertEquals(11, skynet.getCaminhoCurto().getPredecessores()[6]);

        //Checando tempo
        assertEquals(6.5, skynet.getCaminhoCurto().getDistancia()[6]);
        assertEquals(9.5, skynet.getCaminhoCurto().getDistancia()[40]);
        assertEquals(12.5, skynet.getCaminhoCurto().getDistancia()[5]);
        assertEquals(18, skynet.getCaminhoCurto().getDistancia()[39]); 
    }
    
    @Test
    public void caminhoCurtoPrecoTest1(){
        assertDoesNotThrow(() -> skynet.caminhoCurtoPreco("Madrid", "Pequim"));
        //Madrid (R$0,00) -> Londres (R$450,00) -> Oslo (R$1000,00) -> Moscou (R$2450,00) -> Pequim (R$4950,00)

        //Checando predecessores
        assertEquals(18, skynet.getCaminhoCurto().getPredecessores()[24]);
        assertEquals(19, skynet.getCaminhoCurto().getPredecessores()[18]);
        assertEquals(14, skynet.getCaminhoCurto().getPredecessores()[19]);
        assertEquals(12, skynet.getCaminhoCurto().getPredecessores()[14]);

        //Checando preços
        assertEquals(450, skynet.getCaminhoCurto().getDistancia()[14]);
        assertEquals(1000, skynet.getCaminhoCurto().getDistancia()[19]);
        assertEquals(2450, skynet.getCaminhoCurto().getDistancia()[18]);
        assertEquals(4950, skynet.getCaminhoCurto().getDistancia()[24]);
    }
    
    @Test
    public void caminhoCurtoPrecoTest2(){
        assertDoesNotThrow(() -> skynet.caminhoCurtoPreco("Roma", "Nova Delhi"));
            
        //Roma (R$0,00) -> Cairo (R$1800,00) -> Meca (R$2600,00) -> Dubai (R$3800,00) -> Nova Delhi (R$5200,00)
            
        //Checando predecessores
        assertEquals(27, skynet.getCaminhoCurto().getPredecessores()[23]);
        assertEquals(28, skynet.getCaminhoCurto().getPredecessores()[27]);
        assertEquals(22, skynet.getCaminhoCurto().getPredecessores()[28]);
        assertEquals(16, skynet.getCaminhoCurto().getPredecessores()[22]);

        //Checando preços
        assertEquals(1800, skynet.getCaminhoCurto().getDistancia()[22]);
        assertEquals(2600, skynet.getCaminhoCurto().getDistancia()[28]);
        assertEquals(3800, skynet.getCaminhoCurto().getDistancia()[27]);
        assertEquals(5200, skynet.getCaminhoCurto().getDistancia()[23]);
    }
}   

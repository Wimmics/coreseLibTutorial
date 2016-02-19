package fr.inria.wimmics.corese.tutorial.lib;
import fr.inria.acacia.corese.exceptions.EngineException;
import fr.inria.edelweiss.kgram.core.Mappings;
import fr.inria.edelweiss.kgraph.core.Graph;
import fr.inria.edelweiss.kgraph.query.QueryProcess;
import fr.inria.edelweiss.kgtool.load.Load;
import fr.inria.edelweiss.kgtool.load.LoadException;
import fr.inria.edelweiss.kgtool.print.ResultFormat;
import fr.inria.edelweiss.kgtool.print.TripleFormat;
/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws EngineException, LoadException
    {
                Graph graph = Graph.create();
                Load ld = Load.create(graph);
                ld.loadWE("human_2007_09_11.rdf");
                QueryProcess exec = QueryProcess.create(graph);
                String query = "select * where {?x ?p ?y}";
                Mappings map = exec.query(query);
                ResultFormat f1 = ResultFormat.create(map);
                System.out.println(f1);
                TripleFormat f2 = TripleFormat.create(graph);
                System.out.println(f2);
    }
}
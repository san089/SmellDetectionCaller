package padl.visitor.repository;

import padl.kernel.*;
import padl.visitor.IWalker;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;

import java.util.ArrayList;
import java.util.Iterator;

public class PlantUMLVisitor implements IWalker {

    private String result = "@startuml\n";

    public PlantUMLVisitor()
    {
    }

    private ArrayList<String> attributes= new ArrayList<>();
    private ArrayList<String> functions= new ArrayList<>();
    private ArrayList<String> aggregation_relations= new ArrayList<>();
    private ArrayList<String> comps= new ArrayList<>();
    private ArrayList<String> relatedObjects= new ArrayList<>();

    @Override
    public void close(IClass iclass) {
// TODO Auto-generated method stub
        for (String s : functions) {
            result = result+"\r\n" + iclass.getDisplayName()+ " : " + s + "()";
        }
        result=result + "\r\n";
        for (String asc : relatedObjects) {
            result = result + "\r\n" + iclass.getDisplayName() + " <|-- " + asc ;
        }
        for (String agg : aggregation_relations) {
            result = result+"\r\n" + iclass.getDisplayName() + " o-- " + agg ;
        }
        for (String f: attributes) {
            result = result + "\r" + iclass.getDisplayName() + " : " + f ;
        }
        result=result + "\r\n";
        for (String cmp : comps) {
            result = result+"\r\n" + iclass.getDisplayName() + " *-- " + cmp ;
        }
//clearing all the arraylists
        functions.clear();
        relatedObjects.clear();
        attributes.clear();
        aggregation_relations.clear();
        comps.clear();
    }
    @Override
    public void close(IAbstractModel anAbstractModel) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IConstructor aConstructor) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IDelegatingMethod aDelegatingMethod) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IGetter aGetter) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IGhost aGhost) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IInterface anInterface) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IMemberClass aMemberClass) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IMemberGhost aMemberGhost) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IMemberInterface aMemberInterface) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IMethod aMethod) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IPackage aPackage) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(IPackageDefault aPackage) {
// TODO Auto-generated method stub
    }
    @Override
    public void close(ISetter aSetter) {
// TODO Auto-generated method stub
    }
    @Override
    public String getName() {
// TODO Auto-generated method stub
        return null;
    }
    @Override
    public void open(IAbstractModel anAbstractModel) {
// TODO Auto-generated method stub
    }


    @Override
    public void open(IClass iclass) {
// TODO Auto-generated method stub

//clearing the arraylist
        functions.clear();
        relatedObjects.clear();
        attributes.clear();
        aggregation_relations.clear();
        comps.clear();
        result= result +"\nclass "+iclass.getDisplayName()+ " \r\n";
        Iterator interfaces = iclass.getIteratorOnImplementedInterfaces();
        while (interfaces.hasNext()) {
            IInterface aInterface= (IInterface) interfaces.next();
            result= result+"\r\n" + aInterface.getDisplayName() + " -> " + iclass.getDisplayName() ;
        }
    }




    @Override
    public void open(IConstructor aConstructor) {
// TODO Auto-generated method stub
    }
    @Override
    public void open(IDelegatingMethod aDelegatingMethod) {
        // TODO Auto-generated method stub
    }
    @Override
    public void open(IGetter aGetter) {
        // TODO Auto-generated method stub
        functions.add(aGetter.getDisplayName());
    }
    @Override
    public void open(IGhost aGhost) {
// TODO Auto-generated method stub
    }
    @Override
    public void open(IInterface anInterface) {
// TODO Auto-generated method stub
        result= result +"interface "+anInterface.getDisplayName();
    }
    @Override
    public void open(IMemberClass aMemberClass) {
// TODO Auto-generated method stub
    }
    @Override
    public void open(IMemberGhost aMemberGhost) {
// TODO Auto-generated method stub
    }
    @Override
    public void open(IMemberInterface aMemberInterface) {
// TODO Auto-generated method stub
    }
    @Override
    public void open(IMethod aMethod) {
// TODO Auto-generated method stub
//result=result + "->"+ aMethod.getDisplayName() ;
        functions.add(aMethod.getDisplayName());
    }
    @Override
    public void open(IPackage aPackage) {
// TODO Auto-generated method stub
    }
    @Override
    public void open(IPackageDefault aPackage) {
// TODO Auto-generated method stub
    }
    @Override
    public void open(ISetter aSetter) {
// TODO Auto-generated method stub
        functions.add(aSetter.getDisplayName());
    }
    @Override
    public void reset() {
// TODO Auto-generated method stub
    }
    @Override
    public void unknownConstituentHandler(String aCalledMethodName, IConstituent aConstituent) {
// TODO Auto-generated method stub
    }
    @Override
    public void visit(IAggregation anAggregation) {
// TODO Auto-generated method stub
        IFirstClassEntity firstClass = anAggregation.getTargetEntity();
        aggregation_relations.add(firstClass.getDisplayName());
        if(!(firstClass instanceof IGhost))
        {
            aggregation_relations.add(firstClass.getDisplayName());
        }
    }
    @Override
    public void visit(IAssociation anAssociation) {
// TODO Auto-generated method stub
        IFirstClassEntity firstClass= anAssociation.getTargetEntity();
        relatedObjects.add(firstClass.getDisplayName());
        relatedObjects.add(firstClass.getDisplayName());
        if(!(firstClass instanceof IGhost))
            relatedObjects.add(firstClass.getDisplayName());
    }
    @Override
    public void visit(IComposition aComposition) {
// TODO Auto-generated method stub
        IFirstClassEntity firstClass= aComposition.getTargetEntity();
        comps.add(firstClass.getDisplayName());
        if(!(firstClass instanceof IGhost))
            comps.add(firstClass.getDisplayName());
    }
    @Override
    public void visit(IContainerAggregation aContainerAggregation) {
// TODO Auto-generated method stub
    }
    @Override
    public void visit(IContainerComposition aContainerComposition) {
// TODO Auto-generated method stub
    }
    @Override
    public void visit(ICreation aCreation) {
// TODO Auto-generated method stub
    }
    @Override
    public void visit(IField aField) {
// TODO Auto-generated method stub
        attributes.add(aField.getDisplayName());
    }
    @Override
    public void visit(IMethodInvocation aMethodInvocation) {
// TODO Auto-generated method stub
    }
    @Override
    public void visit(IParameter aParameter) {
// TODO Auto-generated method stub
    }
    @Override
    public void visit(IPrimitiveEntity aPrimitiveEntity) {
// TODO Auto-generated method stub
    }
    @Override
    public void visit(IUseRelationship aUse) {
        IFirstClassEntity firstClassEntity = aUse.getTargetEntity();


// TODO Auto-generated method stub
    }
    @Override
    public Object getResult() {
// TODO Auto-generated method stub
        this.result = this.result + "\n@enduml";
        return this.result;
    }

}

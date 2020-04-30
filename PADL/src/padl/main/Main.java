package padl.main;

import padl.analysis.UnsupportedSourceModelException;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.impl.Factory;
import padl.visitor.repository.*;

public class Main {

    public static void main(String[] args) {

        ICodeLevelModel codeLevelModel = createAnnotatedPadlClassModel("D:\\PADL_Workspace\\TestProject\\bin\\");
        String s = codeLevelModel.walk(new PlantUMLVisitor()).toString();
        System.out.println(codeLevelModel.getNumberOfConstituents());
        System.out.println("\n===========================\n" + s + "\n===========================\n");
    }

    public static ICodeLevelModel createAnnotatedPadlClassModel(final String aBinPath) {

        ICodeLevelModel codeLevelModel =
                Factory.getInstance().createCodeLevelModel("ddd");
//		        final ModelStatistics statisticModelListener = new ModelStatistics();
//		        codeLevelModel.addModelListener(statisticModelListener);

        try {
            codeLevelModel.create(new CompleteClassFileCreator(
                    new String[] { aBinPath },
                    true));

            final padl.statement.creator.classfiles.ConditionalModelAnnotator annotator =
                    new padl.statement.creator.classfiles.ConditionalModelAnnotator(
                            new String[] { aBinPath });
            final ICodeLevelModel annotatedCodeLevelModel =
                    (ICodeLevelModel) annotator.invoke(codeLevelModel);

            final padl.statement.creator.classfiles.LOCModelAnnotator annotator2 =
                    new padl.statement.creator.classfiles.LOCModelAnnotator(
                            new String[] { aBinPath });

            codeLevelModel =
                    (ICodeLevelModel) annotator2.invoke(annotatedCodeLevelModel);

            return codeLevelModel;

        }
        catch (final UnsupportedSourceModelException | padl.kernel.exception.CreationException e) {
            e.printStackTrace();
        }
        return null;

    }

}




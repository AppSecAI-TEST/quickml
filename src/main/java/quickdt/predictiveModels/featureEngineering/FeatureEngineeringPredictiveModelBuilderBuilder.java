package quickdt.predictiveModels.featureEngineering;

import quickdt.data.MapWithDefaultOfZero;
import quickdt.predictiveModelOptimizer.FieldValueRecommender;
import quickdt.predictiveModels.Classifier;
import quickdt.predictiveModels.PredictiveModel;
import quickdt.predictiveModels.PredictiveModelBuilderBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ian on 5/21/14.
 */
public class FeatureEngineeringPredictiveModelBuilderBuilder implements PredictiveModelBuilderBuilder<Map<String, Serializable>,FeatureEngineeredPredictiveModel, FeatureEngineeringPredictiveModelBuilder> {

    private final PredictiveModelBuilderBuilder<Map<String, Serializable>, PredictiveModel<Map<String, Serializable>, MapWithDefaultOfZero>, ?> wrappedBuilderBuilder;
    private final List<? extends AttributesEnrichStrategy> enrichStrategies;

    public FeatureEngineeringPredictiveModelBuilderBuilder(
            PredictiveModelBuilderBuilder<Map<String, Serializable>, PredictiveModel<Map<String, Serializable>, MapWithDefaultOfZero>, ?> wrappedBuilderBuilder,
            List<? extends AttributesEnrichStrategy> enrichStrategies) {
        this.wrappedBuilderBuilder = wrappedBuilderBuilder;
        this.enrichStrategies = enrichStrategies;
    }

    @Override
    public Map<String, FieldValueRecommender> createDefaultParametersToOptimize() {
        // Currently we don't have any parameters for this builderBuilder
        return wrappedBuilderBuilder.createDefaultParametersToOptimize();
    }

    @Override
    public FeatureEngineeringPredictiveModelBuilder buildBuilder(final Map<String, Object> predictiveModelConfig) {
        return new FeatureEngineeringPredictiveModelBuilder(wrappedBuilderBuilder.buildBuilder(predictiveModelConfig), enrichStrategies);
    }
}

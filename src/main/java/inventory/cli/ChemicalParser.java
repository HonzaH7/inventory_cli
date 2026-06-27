package inventory.cli;

import inventory.domain.Category;
import inventory.domain.Chemical;
import inventory.domain.Hazard;
import inventory.exception.InvalidChemicalException;

import java.util.EnumSet;
import java.util.Set;

public class ChemicalParser {

    public Chemical fromInput(String name, String id, String categoryText,
                              String concentrationText, String hazardsText) {
        Category category;
        try {
            category = Category.valueOf(categoryText.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidChemicalException("Unknown category: " + categoryText, e);
        }

        double concentration = Double.parseDouble(concentrationText.trim());

        Set<Hazard> hazards = EnumSet.noneOf(Hazard.class);
        for (String part : hazardsText.split(",")) {
            String token = part.trim().toUpperCase();
            if (!token.isBlank()) {
                hazards.add(Hazard.valueOf(token));
            }
        }

        return new Chemical(name, id, category, concentration, hazards);
    }
}
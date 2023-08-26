package de.woock.infra.converter.selma;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.Anfrage_;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.Mapper;

@Mapper(
    withCustomFields = {
        @Field({"customer.fullName", "customerFullName"}),
        @Field({"reference", "ref"})
    },
    withIgnoreFields = "id"
)
public interface SelmaMapper {

    Anfrage_ asAnfrage_(Anfrage in);

    Anfrage asAnfrage(Anfrage_ in, Anfrage out);

}



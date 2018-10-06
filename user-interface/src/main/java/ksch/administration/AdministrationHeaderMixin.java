package ksch.administration;

import ksch.HeaderMixin;

public interface AdministrationHeaderMixin extends HeaderMixin {

    default String getHomePagePath() {
        return "/administration";
    }
}

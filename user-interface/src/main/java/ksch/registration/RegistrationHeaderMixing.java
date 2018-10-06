package ksch.registration;

import ksch.HeaderMixin;

public interface RegistrationHeaderMixing extends HeaderMixin {

    default String getHomePagePath() {
        return "/registration";
    }
}

package io.github.kschworkflows.services.staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class User
{
    private String name;

    @NonNull
    private final String login;

    @NonNull
    private final String password;

    @NonNull
    private final String roles;
}

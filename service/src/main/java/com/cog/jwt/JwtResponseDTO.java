package com.cog.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates getters, setters
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {

  // Name of the ingredient (should not be blank)
  @NotBlank(message = "accessToken must not be blank")

  // Phone number of the customer (should not be blank)
  // @NotNull(message = "accessToken is required")
  private String accessToken;
}

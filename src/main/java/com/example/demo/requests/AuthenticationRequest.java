/*
 * AMELA Technology JSC
 *
 * NOTICE:  All source code, documentation and other information
 * contained herein is, and remains the property of Thor-Amela.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained from Thor-Amela.
 */
package com.example.demo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("password")
    private String password;
}

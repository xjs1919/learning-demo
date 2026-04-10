package com.github.xjs.vo;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactVO {
    private Long id;
    private String mobile;
    private String email;
}

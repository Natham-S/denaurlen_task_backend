package com.natham.denaurlen_task_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Valuation {
    private String name;
    private String username;
    private int netCoins;
    private int grossCoins;
}
//
//  BalanceModel.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 28/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//


import Foundation

struct AccountBalancess: Decodable {
    let accountCode: String?
    let accruedCharge: Double?
    let assetValue: Double?
    let buyingPower: Double?
    let cashBalance: Double?
    let costValue: Double?
    let currentBalance: Double?
    let deptEquityRatio: Double?
    let equity: Double?
    let equityDebtRatio: Double?
    let immatureBalance: Double?
    let loanRatio: Double?
    let marginEquity: Double?
    let marketValue: Double?
    let totalDeposit: Double?
    let totalWithdrawal: Double?
    let unclearCheque: Double?
}


import Foundation

struct FinancialData {
    let accountCode: String
    let accruedCharge: String
    let assetValue: String
    let buyingPower: String
    let cashBalance: String
    let costValue: String
    let currentBalance: String
    let deptEquityRatio: String
    let equity: String
    let equityDebtRatio: String
    let immatureBalance: String
    let loanRatio: String
    let marginEquity: String
    let marketValue: String
    let totalDeposit: String
    let totalWithdrawal: String
    let unclearCheque: String
}

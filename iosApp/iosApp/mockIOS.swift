//
//  mockIOS.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 25/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

class MockLoader {
    
    let databaseDriverFactory: DatabaseDriverFactory
    let service: MockService
    
    init() {
        self.databaseDriverFactory = DatabaseDriverFactory()
        self.service = MockService(databaseDriverFactory: self.databaseDriverFactory)
    }
   
    static func loadFinancialData() -> [FinancialData] {
        var financialData: [FinancialData] = []
        
        let loader = MockLoader() // Create an instance of MockLoader to access instance properties
        
        // Load JSON data from file
        if let path = Bundle.main.path(forResource: "accountbalance", ofType: "json") {
            do {
                let data = try Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
                let jsonResult = try JSONSerialization.jsonObject(with: data, options: .mutableLeaves)
                
                // Access the JSON data
                if let jsonDataArray = jsonResult as? [[String: String]] {
                    for dataEntry in jsonDataArray {
                        if let accountCode = dataEntry["accountCode"],
                           let accruedCharge = dataEntry["accruedCharge"],
                           let assetValue = dataEntry["assetValue"],
                           let buyingPower = dataEntry["buyingPower"],
                           let cashBalance = dataEntry["cashBalance"],
                           let costValue = dataEntry["costValue"],
                           let currentBalance = dataEntry["currentBalance"],
                           let deptEquityRatio = dataEntry["deptEquityRatio"],
                           let equity = dataEntry["equity"],
                           let equityDebtRatio = dataEntry["equityDebtRatio"],
                           let immatureBalance = dataEntry["immatureBalance"],
                           let loanRatio = dataEntry["loanRatio"],
                           let marginEquity = dataEntry["marginEquity"],
                           let marketValue = dataEntry["marketValue"],
                           let totalDeposit = dataEntry["totalDeposit"],
                           let totalWithdrawal = dataEntry["totalWithdrawal"],
                           let unclearCheque = dataEntry["unclearCheque"] {
                            
                            let financialDataEntry = FinancialData(
                                accountCode: accountCode,
                                accruedCharge: accruedCharge,
                                assetValue: assetValue,
                                buyingPower: buyingPower,
                                cashBalance: cashBalance,
                                costValue: costValue,
                                currentBalance: currentBalance,
                                deptEquityRatio: deptEquityRatio,
                                equity: equity,
                                equityDebtRatio: equityDebtRatio,
                                immatureBalance: immatureBalance,
                                loanRatio: loanRatio,
                                marginEquity: marginEquity,
                                marketValue: marketValue,
                                totalDeposit: totalDeposit,
                                totalWithdrawal: totalWithdrawal,
                                unclearCheque: unclearCheque
                            )
                            
                            financialData.append(financialDataEntry)
                        }
                    }
                }
            } catch {
                print("Error reading JSON file:", error.localizedDescription)
            }
        }
        // Use service to load account balance
        let accountBalances = financialData.map { $0.toAccountBalance() }
            loader.service.loadAccountBalance(balances: accountBalances)

        
        // Use service to load account balance from the created instance
        //loader.service.loadAccountBalance(balances: financialData)
        
        return financialData
    }
}

extension FinancialData {
    func toAccountBalance() -> AccountBalance {
        let accountCode = self.accountCode
        let accruedCharge = KotlinDouble(pointer: self.accruedCharge)
        let assetValue = KotlinDouble(pointer: self.assetValue)
        let buyingPower = KotlinDouble(pointer: self.buyingPower)
        let cashBalance = KotlinDouble(pointer: self.cashBalance)
        let costValue = KotlinDouble(pointer: self.costValue)
        let currentBalance = KotlinDouble(pointer: self.currentBalance)
        let deptEquityRatio = KotlinDouble(pointer: self.deptEquityRatio)
        let equity = KotlinDouble(pointer: self.equity)
        let equityDebtRatio = KotlinDouble(pointer: self.equityDebtRatio)
        let immatureBalance = KotlinDouble(pointer: self.immatureBalance)
        let loanRatio = KotlinDouble(pointer: self.loanRatio)
        let marginEquity = KotlinDouble(pointer: self.marginEquity)
        let marketValue = KotlinDouble(pointer: self.marketValue)
        let totalDeposit = KotlinDouble(pointer: self.totalDeposit)
        let totalWithdrawal = KotlinDouble(pointer: self.totalWithdrawal)
        let unclearCheque = KotlinDouble(pointer: self.unclearCheque)
        
        return AccountBalance(
            accountCode: accountCode,
            accruedCharge: accruedCharge,
            assetValue: assetValue,
            buyingPower: buyingPower,
            cashBalance: cashBalance,
            costValue: costValue,
            currentBalance: currentBalance,
            deptEquityRatio: deptEquityRatio,
            equity: equity,
            equityDebtRatio: equityDebtRatio,
            immatureBalance: immatureBalance,
            loanRatio: loanRatio,
            marginEquity: marginEquity,
            marketValue: marketValue,
            totalDeposit: totalDeposit,
            totalWithdrawal: totalWithdrawal,
            unclearCheque: unclearCheque
        )
    }
}





/*
class MockLoader {
    let databaseDriverFactory: DatabaseDriverFactory

    init() {
        self.databaseDriverFactory = DatabaseDriverFactory()
    }

    func `init`() {
        var isLoaded = false
        if isLoaded {
            return
        }
        
        let service = MockService(databaseDriverFactory: databaseDriverFactory)
 
 
        /*
        // Account Transaction
        if let transactionData = loadJson(fileName: "accounttransaction") {
            do {
                let transactions = try JSONDecoder().decode([AccountTransaction].self, from: transactionData)
                service.loadAccountTransaction(transactions: transactions)
            } catch {
                print("Error decoding account transaction data: \(error.localizedDescription)")
            }
        }
*/
        
        // Account Balance
        if let balanceData = loadJson(fileName: "accountbalance") {
            
            do {
                let balances = try JSONDecoder().decode([AccountBalance].self, from: balanceData)
                service.loadAccountBalance(balances: balances)
            } catch {
                print("Error decoding account balance data: \(error.localizedDescription)")
            }
        }

        isLoaded = true
    }

    func loadJson(fileName: String) -> Data? {
        if let path = Bundle.main.path(forResource: fileName, ofType: "json") {
            do {
                let data = try Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
                return data
            } catch {
                print("Error loading JSON file: \(error.localizedDescription)")
            }
        }
        return nil
    }
}
*/

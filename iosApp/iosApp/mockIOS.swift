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

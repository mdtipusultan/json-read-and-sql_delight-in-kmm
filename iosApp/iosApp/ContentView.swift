import SwiftUI
import shared

struct ContentView: View {
    let financialData: [FinancialData] = MockLoader.loadFinancialData()

    var body: some View {
        NavigationView {
            List(financialData, id: \.accountCode) { data in
                VStack(alignment: .leading) {
                    Text("Account Code: \(data.accountCode)")
                    Text("Cash Balance: \(data.cashBalance)")
                    Text("Current Balance: \(data.currentBalance)")
                    // Add more Text views for other fields as needed
                }
            }
            .navigationTitle("Financial Data")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


package com.graphql.subscriptionusergraphql;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> users = Arrays.asList(
            ImmutableMap.of("id", "user-1",
                    "first name", "Paul",
                    "last name", "Ladmirault",
                    "walletID", "wallet-1"),
            ImmutableMap.of("id", "user-2",
                    "first name", "Nima",
                    "last name", "Golsharifi",
                    "walletID", "wallet-2"),
            ImmutableMap.of("id", "user-3",
                    "name", "Giorgi",
                    "last name", "Gogokhia",
                    "walletId", "wallet-3")
    );

    private static List<Map<String, String>> currencies = Arrays.asList(
            ImmutableMap.of("id", "wallet-1",
                    "ETH", "1,67",
                    "BTC", "0,32",
                    "USD","5,789.87"),
            ImmutableMap.of("id", "wallet-2",
                    "ETH", "4,09",
                    "BTC", "2,87",
                    "USD","17,053.16"),
            ImmutableMap.of("id", "wallet-3",
                    "ETH", "6,63",
                    "BTC", "4,34",
                    "USD","15,282.02")
    );

    public DataFetcher getUserByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String userId = dataFetchingEnvironment.getArgument("id");
            return users
                    .stream()
                    .filter(user -> user.get("id").equals(userId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getCurrencyDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> user = dataFetchingEnvironment.getSource();
            String currencyId = user.get("currencyId");
            return currencies
                    .stream()
                    .filter(currency -> currency.get("id").equals(currencyId))
                    .findFirst()
                    .orElse(null);
        };
    }

}

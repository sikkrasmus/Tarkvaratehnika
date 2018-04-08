<template>
  <v-container>
    <input type="text" v-model="value" class="search-box" placeholder="Search here">
    <v-expansion-panel>
      <v-expansion-panel-content v-for="data in computedDatas" :key="data">
        <div slot="header">{{data}}</div>
        <v-divider></v-divider>
        <v-card>
          <v-container grid-list id="dropdown-example">
            <v-layout row wrap>
              <v-flex xs12 sm12>
                <v-select
                  :items="exchanges"
                  label="Exchange"
                  target="#dropdown-example"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-container>
          <v-card-text>
            <v-form>
              <v-text-field
                label="Amount"
                v-model="amount"
              ></v-text-field>
              <v-text-field
                label="Price"
                v-model="price"
              ></v-text-field>
              <v-btn outline color="indigo">Add</v-btn>
            </v-form>
          </v-card-text>
        </v-card>
        <div v-show="!haveResults" class="no-result">
          No result found !
        </div>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-container>
</template>

<script>
  export default {
    name: 'coinsearch',
    data() {
      return {
        haveResults: true,
        value: "",
        amount: '',
        price: '',
        exchanges: ['Bittrex', 'Binance'],
        datas: [
          "VeChain",
          "Neo",
          "Bitcoin",
          "Tron",
          "Ripple",
          "Mithril",
          "Cardano",
          "Bitcoin gold",
          "Litecoin"]
      }
    },
    computed: {
      computedDatas: function () {
        var result = this.datas.filter((value) => {
          return value.toLowerCase().includes(this.value.toLowerCase());
        });
        if (this.value != "") {
          result = result.map((value) => {
            return value.replace(this.value, this.value)
          });
        }
        if (result.length == 0) {
          this.haveResults = false;
        }
        else {
          this.haveResults = true;
        }
        return result;
      }
    }
  }
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css?family=Montserrat');

  * {
    box-sizing: border-box;
    font-family: 'Montserrat', sans-serif;
  }

  .search-box {
    display: block;
    width: 100%;
    height: 45px;
    margin: 10px auto;
    border: none;
    outline: 0;
    box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.2);
    border-radius: 4px;
    background-color: #ececec;
    padding: 0 20px;
    font-size: 13px;
  }

  .data {
    width: 100%;
    background-color: #4facfe;
    background-image: linear-gradient(45deg, #4facfe, #667eea);
    color: rgba(255, 255, 255, 0.6);
    height: 45px;
    line-height: 45px;
    margin: 5px 0 0 0;
    padding: 0 15px;
    border-radius: 4px;
    font-size: 13px;
    box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);
  }

  .result {
    font-weight: 900;
    color: rgba(255, 255, 255, 1);
  }

  .no-result {
    width: 100%;
    background-color: #ff0844;
    background-image: linear-gradient(45deg, #ff0844, #ffb199);
    color: #fff;
    height: 45px;
    line-height: 45px;
    margin: 5px 0 0 0;
    padding: 0 15px;
    border-radius: 4px;
    font-size: 13px;
    box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);
  }

  a {
    text-decoration: none;
    color: #4facfe;
    font-weight: 900;
  }
</style>

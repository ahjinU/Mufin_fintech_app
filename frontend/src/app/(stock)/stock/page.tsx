import { Header, Tab } from '@/components';
import MainMyStock from './_components/MainMyStock';
import MainStockList from './_components/MainStockList';
import { StockAllType } from './_types';
import {
  getRankingMine,
  getRankingTotal,
  postParkingAccount,
  postStockMine,
  postStocksAll,
} from './_apis';
import useStockStore from './_store';
const url = `${process.env.REACT_APP_WEATHER_API}?q=Seoul&appid=${process.env.REACT_APP_WEATHER_API_KEY}`;
import { useServerPostFetch } from '@/hooks/useServerFetch';

export interface DataType {
  temp: number;
  description: number;
  stocks: StockAllType[];
}

export default async function Stock() {
  const { updateRanks, updateMyRank, updateMyParking, updateMyStock } =
    useStockStore.getState();

  // const ranks = await getRankingTotal();
  // updateRanks(ranks?.data);
  // const myRank = await getRankingMine();
  // updateMyRank(myRank?.data);
  // const myStocks = await postStockMine();

  // const myStocksData = await myStocks.json();
  // console.log(myStocksData.data);
  const myStocks = await useServerPostFetch({ api: '/api/stock/mine' });
  console.log(myStocks.data);
  // updateMyStock(myStocks?.data);
  // const myParking = await postParkingAccount();
  // updateMyParking(myParking?.data);

  // const stocks = await postStocksAll();
  const weatherRes = await fetch(url, { cache: 'no-cache' });
  const weather = await weatherRes.json();

  const mainTabData = {
    temp: parseFloat((weather.main.temp - 273.15).toFixed(2)),
    description: weather.weather[0].id,
    // stocks: stocks.data.stock,
    stocks: [],
  };

  return (
    <div>
      <Header>
        <h1 className="custom-bold-text">날씨 주식</h1>
      </Header>
      <Tab
        tabs={[
          {
            label: '주식 목록',
            component: <MainStockList data={mainTabData} />,
          },
          {
            label: '내 잔고',
            component: <MainMyStock />,
          },
        ]}
      />
    </div>
  );
}

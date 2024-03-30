import { Header, Tab } from '@/components';
import MainMyStock from './_components/MainMyStock';
import MainStockList from './_components/MainStockList';
import { StockAllType, RankType } from './_types';
const url = `${process.env.REACT_APP_WEATHER_API}?q=Seoul&appid=${process.env.REACT_APP_WEATHER_API_KEY}`;
import { serverGetFetch, serverPostFetch } from '@/hooks/useServerFetch';

export interface DataType {
  temp: number;
  description: number;
  stocks: StockAllType[];
  ranks: RankType[];
  myRank: RankType;
}

export default async function Stock() {
  // 전체 주식 정보 가져오기
  const stocks = await serverPostFetch({ api: '/stock/all' });
  // console.log(stocks.data);

  // 내 보유 주식 정보 가져오기
  const myStocks = await serverPostFetch({ api: '/stock/mine' });
  // console.log(myStocks);

  // 내 파킹 통장 정보 가져오기
  const myParking = await serverPostFetch({ api: '/parking/account' });
  // console.log(myParking.data);

  // 전체 랭킹 정보 가져오기
  const ranks = await serverGetFetch({ api: '/stock/ranking/total' });
  // console.log(ranks.data);

  // 내 랭킹 정보 가져오기
  const myRank = await serverGetFetch({ api: '/stock/ranking/user' });
  // console.log(myRank.data);

  // 날씨 정보 가져오기
  const weatherRes = await fetch(url, { cache: 'no-cache' });
  const weather = await weatherRes.json();

  const mainTabData = {
    temp: parseFloat((weather.main.temp - 273.15).toFixed(2)),
    description: weather.weather[0].id,
    stocks: stocks.data.stock,
    ranks: ranks.data.ranks,
    myRank: myRank.data,
  };

  const myStockData = {
    myStocks: myStocks.data,
    myParking: myParking.data,
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
            component: <MainMyStock data={myStockData} />,
          },
        ]}
      />
    </div>
  );
}

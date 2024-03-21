import { Header, Tab } from '@/components';
import MainMyStock from './_components/Main/MainMyStock';
import MainStockList from './_components/Main/MainStockList';

export interface WeatherType {
  temp: number;
  description: number;
}

export default async function Stock() {
  const url = `${process.env.REACT_APP_WEATHER_API}?q=Seoul&appid=${process.env.REACT_APP_WEATHER_API_KEY}`;

  let myStockData: WeatherType = {
    temp: 0,
    description: 0,
  };

  const fetchWeather = async () => {
    const data = await fetch(url)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        return data;
      });
    myStockData = {
      temp: parseFloat((data.main.temp - 273.15).toFixed(2)),
      description: data.weather[0].id,
    };
  };

  await fetchWeather();

  return (
    <div>
      <Header>
        <h1 className="custom-bold-text">날씨 주식</h1>
      </Header>
      <Tab
        tabs={[
          {
            label: '주식목록',
            component: <MainStockList data={myStockData} />,
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

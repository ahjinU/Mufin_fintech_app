import { Header, Tab } from '@/components';
import Calendar from './_components/Calendar';
const url = `${process.env.REACT_APP_WEATHER_API}?q=Seoul&appid=${process.env.REACT_APP_WEATHER_API_KEY}`;

export default async function Book() {
  return (
    <div>
      <Header>
        <h1 className="custom-bold-text">용돈 가계부</h1>
      </Header>
      <div className="p-[1.2rem] flex flex-col gap-[1rem]">
        <Calendar />
      </div>
    </div>
  );
}

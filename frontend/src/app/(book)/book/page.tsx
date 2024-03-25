import { ComplexInput, Header, MoneyShow, Tab } from '@/components';
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
        <ComplexInput label={'이번 달 내역'} mode={'NONE'}>
          <MoneyShow
            mode={'DIVIDED'}
            text={['지출', '수입']}
            money={['-92000', '19000']}
            unit={'원'}
          />
        </ComplexInput>
      </div>
    </div>
  );
}

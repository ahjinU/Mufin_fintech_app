import { Header, Tab } from '@/components';
import MainStockList from './_components/MainStockList';

export default function Stock() {
  return (
    <div>
      <Header>
        <h1 className="custom-bold-text">날씨 주식</h1>
      </Header>
      <Tab
        tabs={[
          {
            label: '주식목록',
            component: <MainStockList />,
          },
          {
            label: '내 잔고',
            component: <MainStockList />,
          },
        ]}
      />
    </div>
  );
}

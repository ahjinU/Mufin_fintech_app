import {
  BackButton,
  FlexBox,
  Header,
  MoneyInfoElement,
  OtherInfoElement,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import MyStockInfo from './MyStockInfo';
import { StockInfo } from '../_types';
import { useServerPostFetch } from '@/hooks/useServerFetch';

export default async function StockList() {
  const myStocks = await useServerPostFetch({ api: '/stock/mine' });
  const { totalPrice, totalIncome, myStockList } = myStocks?.data;

  return (
    <div className="pb-[1rem]">
      <Header>
        <BackButton label={'내 보유 주식'} />
      </Header>
      <div className="flex flex-col px-[1rem] w-full gap-[1rem]">
        <FlexBox
          isDivided={false}
          topChildren={
            <MoneyInfoElement
              imageSrc={'/images/icon-stock.png'}
              leftExplainText={'내 주식 평가'}
              leftHighlightText={`${commaNum(totalPrice)} 초콜릿`}
              buttonOption={`${totalIncome < 0 ? 'STOCK_DOWN' : 'STOCK_UP'}`}
              stockPrice={`${commaNum(totalIncome)} 초콜릿`}
            />
          }
        />
        {myStockList?.map((stock: StockInfo, index: number) => {
          const { name, cnt, income, incomeRatio } = stock;
          return (
            <FlexBox
              key={`mystocks-${index}`}
              isDivided={true}
              topChildren={
                <OtherInfoElement
                  leftExplainText={`${commaNum(cnt)}주`}
                  leftHighlightText={`${name}`}
                  state={`${incomeRatio < 0 ? 'DOWN' : 'UP'}`}
                  rightHighlightText={`${commaNum(income)} 초코칩`}
                  rightExplainText={`(${commaNum(incomeRatio)}%)`}
                />
              }
              bottomChildren={<MyStockInfo stock={stock} />}
            />
          );
        })}
      </div>
    </div>
  );
}

import {
  BackButton,
  FlexBox,
  Header,
  MoneyInfoElement,
  OtherInfoElement,
} from '@/components';
import useStockStore from '../_store';
import { commaNum } from '@/utils/commaNum';
import MyStockInfo from './MyStockInfo';

export default function StockList() {
  const { myStock } = useStockStore.getState();
  const { totalPrice, totalIncome, myStockList } = myStock;
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
              leftHighlightText={`${commaNum(totalPrice)}초콜릿`}
              buttonOption={`${totalIncome < 0 ? 'STOCK_DOWN' : 'STOCK_UP'}`}
              stockPrice={`${commaNum(totalIncome)}초콜릿`}
            />
          }
        />
        {myStockList?.map((stock, index) => {
          const { name, cnt, income, incomeRatio } = stock;
          return (
            <FlexBox
              key={`mystocks-${index}`}
              isDivided={true}
              topChildren={
                <OtherInfoElement
                  leftExplainText={`${cnt}주`}
                  leftHighlightText={`${name}`}
                  state={`${incomeRatio < 0 ? 'DOWN' : 'UP'}`}
                  rightHighlightText={`${income}초코칩`}
                  rightExplainText={`(${incomeRatio}%)`}
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

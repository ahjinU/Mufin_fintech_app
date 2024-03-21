import {
  BackButton,
  FlexBox,
  GuideText,
  Header,
  Input,
  MoneyInfoElement,
  OtherInfoElement,
  Tab,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import StorageList from './_compoents/StorageList';
import PendingList from './_compoents/PendingList';

interface MyChoco {
  balanceToday: number;
  ratio: number;
  balanceTmrw: number;
  interest: number;
}

export interface TransactionType {
  transName: string; // 우산회사
  amount: number; // 18,000 자수민
  type: string; // 이자, 매도, 매수
  cnt: number; // 체결 수
  price: number; // 매도/매수 일경우 : 18000/2 = 9000 자스만
  ratio: number; // 이자 일 경우 : 파킹통장 이자율
  date: string;
}

export default function StockStroage() {
  const myChocoData: MyChoco = {
    balanceToday: 1724900,
    ratio: 0,
    balanceTmrw: 1725000,
    interest: 100,
  };

  const storageList: TransactionType[] = [
    {
      transName: '우산회사',
      amount: 18000,
      type: '이자',
      cnt: 5,
      price: 0,
      ratio: 0.03,
      date: '2024년 03월 21일',
    },
    {
      transName: '자수민',
      amount: 18000,
      type: '매도',
      cnt: 3,
      price: 9000,
      ratio: 0,
      date: '2024년 03월 21일',
    },
    {
      transName: '자스만',
      amount: 18000,
      type: '매수',
      cnt: 2,
      price: 9000,
      ratio: 0,
      date: '2024년 03월 21일',
    },
  ];

  return (
    <div>
      <Header>
        <BackButton label={'내 초코칩 보관함'} />
      </Header>
      <div className="flex flex-col px-[1rem] gap-[1rem]">
        <FlexBox
          isDivided={true}
          topChildren={
            <MoneyInfoElement
              imageSrc={'/images/icon-my-chocochips.png'}
              leftExplainText={'내 초코칩 보관함'}
              leftHighlightText={`${commaNum(myChocoData.balanceToday)}초코칩`}
              buttonOption={'RATE'}
            />
          }
          bottomChildren={
            <OtherInfoElement
              leftExplainText={`이자로 ${myChocoData.ratio}%를 더 받아요`}
              leftHighlightText={`내일 내 초코칩`}
              rightHighlightText={`${commaNum(myChocoData.balanceTmrw)}초코칩`}
              rightExplainText={`+${myChocoData.ratio}초코칩`}
              state={'DOWN'}
            />
          }
        />
        <div>
          <GuideText
            text={'보관함 전체 내역과 최근 6개월 미체결 내역을 볼 수 있어요!'}
          />
          <Tab
            tabs={[
              {
                label: '보관함 내역',
                component: <StorageList list={storageList} />,
              },
              {
                label: '미체결 내역',
                component: <PendingList list={storageList} />,
              },
            ]}
          />
        </div>
      </div>
    </div>
  );
}

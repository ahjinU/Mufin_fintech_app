import { FlexBox, OtherInfoElement } from '@/components';
import { commaNum } from '@/utils/commaNum';
import { TransactionType } from '../../_types';

export default function StorageList({ list }: { list: TransactionType[] }) {
  return (
    <div className="flex flex-col gap-[0.5rem] py-[0.5rem]">
      {list?.map(
        ({ type, transName, date, amount, ratio, cnt, price }, index) => {
          return type === '이자' ? (
            <FlexBox
              mode={'LIST'}
              isDivided={false}
              date={date}
              key={`storage-list-${index}`}
              topChildren={
                <OtherInfoElement
                  leftExplainText={`보관함 이자`}
                  leftHighlightText={`${transName}`}
                  rightExplainText={`(${ratio}%)`}
                  rightHighlightText={`${amount && commaNum(amount)}초코칩`}
                  state={'DOWN'}
                />
              }
            />
          ) : (
            <FlexBox
              mode={'LIST'}
              isDivided={false}
              date={date}
              key={`storage-list-${index}`}
              topChildren={
                <OtherInfoElement
                  leftExplainText={`${type} ${cnt}주`}
                  leftHighlightText={`${transName}`}
                  rightExplainText={`${price && commaNum(price)}초코칩`}
                  rightHighlightText={`${amount && commaNum(amount)}초코칩`}
                  state={`${type === '매수' ? 'UP' : 'DOWN'}`}
                />
              }
            />
          );
        },
      )}
    </div>
  );
}

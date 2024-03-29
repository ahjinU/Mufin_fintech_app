import { FlexBox, OtherInfoElement } from '@/components';
import { commaNum } from '@/utils/commaNum';
import { TransactionType } from '../../_types';

export default function StorageList({ list }: { list: TransactionType[] }) {
  return (
    <div className="flex flex-col gap-[1rem] py-[1.2rem]">
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
                  rightHighlightText={`${amount && commaNum(amount)} 초코칩`}
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
                  leftExplainText={`${type} ${
                    type !== '시드머니' ? cnt + '주' : ''
                  }`}
                  leftHighlightText={`${transName}`}
                  rightExplainText={`${price && commaNum(price)} 초코칩`}
                  rightHighlightText={`${amount && commaNum(amount)} 초코칩`}
                  state={`${
                    type === '매수' || type === '시드머니' ? 'UP' : 'DOWN'
                  }`}
                />
              }
            />
          );
        },
      )}
    </div>
  );
}

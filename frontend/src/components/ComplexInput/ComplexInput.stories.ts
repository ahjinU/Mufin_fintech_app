import type { Meta, StoryObj } from '@storybook/react';

import ComplexInput from './ComplexInput';

const meta = {
  title: 'Common/ComplexInput',
  component: ComplexInput,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
   label: {
    description: '소제목입니다.'
   },
   width: {
    description: 'width를 지정할 수 있습니다'
   },
   children: {
    description: '내부 컴포넌트들은 children으로 보내줍니다.'
   },
   errorMsg: {
    description: 'errorMsg를 지정할 수 있습니다'
   },
   isError: {
    description: '에러메세지 조건이라면 true로 지정해주면 됩니다. 기본 값은 false 입니다.'
   },
  },
} satisfies Meta<typeof ComplexInput
>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    label: '내용을 입력해주세요',
    isError: true,
    errorMsg: '틀렸어요',
  },
};
